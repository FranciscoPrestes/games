package vggames.git

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class GitGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(
    commit)

  def commit = {
    val repo = EmptyGit() ~
      Commit("commit 1") ~
      Commit("commit 2") ~
      Commit("commit 3") ~
      Branch("abc") ~
      Commit("commit 4") ~
      Commit("commit 5") ~
      Checkout("abc") ~
      Commit("caracas") ~
      Commit("papagaio") ~
      Checkout("asdrubal", true)
    Commit("asdasd")

    new TaskGroup("teste", "test", descriptions, repo.tasks : _*)
  }

  def getDescription = "Um jogo muito legal para aprender Git"

  def getName = "Git"

}