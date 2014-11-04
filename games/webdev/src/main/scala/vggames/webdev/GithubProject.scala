package vggames.webdev

import vggames.shared.task.Task
import vggames.shared.task.TaskGroup
import vggames.shared.task.status.Ok
import vggames.shared.log.LogItem
import vggames.shared.Database
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.session.Database.threadLocalSession
import java.util.Date
import java.util.UUID
import java.io.File
import java.text.SimpleDateFormat
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.util.FileUtils

class GithubProject(name : String, id : String)
    extends TaskGroup(name, id, new GithubTask()) {

}

class GithubTask() extends Task {

  def judge(challenge : String) = Ok()

  def challenge = "Crie um projeto no github que resolva o problema apresentado ao lado e coloque a url abaixo"

  def resource = ""

  override def extraLog(playerId : Option[Long], challenge : String, gameName : String) =
    Some(GitRepo(playerId, challenge, gameName))

}

case class GitRepo(playerId : Option[Long], challenge : String, gameName : String) extends LogItem with Database {

  def log = {
    val folder = "repos/" + playerId.map(folderName).getOrElse(UUID.randomUUID().toString)

    onDatabase {
      GitRepoSubmissions.insert(playerId, gameName, challenge, folder)
    }

    Git.cloneRepository().setURI(challenge).setDirectory(new File(folder)).call()
    FileUtils.delete(new File(folder + "/.git"), FileUtils.RECURSIVE)

  }

  private def folderName(i : Long) = {
    val today = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
    s"$today/$i-${new Date().getTime}"
  }

}

object GitRepoSubmissions extends Table[(Option[Long], String, String, String)]("gitRepoSubmissions") {

  def playerId = column[Option[Long]]("playerId")
  def gameName = column[String]("game")
  def repo = column[String]("repo")
  def folder = column[String]("folder")

  def * = playerId ~ gameName ~ repo ~ folder

}