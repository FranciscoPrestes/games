package vggames.scala.specs

import vggames.scala.code.RestrictedFunction2

class DivSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def runSignature = "(a:Int, b:Int):Int"

  def extendsType = "RestrictedFunction2[Int, Int, Int]"

  def getChallenge = "Divida <code>a</code> por <code>b</code>"

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      "dividir a por b e resultar em 3 quando a = 3 e b = 1" in {
        code(3, 1) must_== 3
      }

      "dividir a por b e resultar em 2 quando a = 5 e b = 2" in {
        code(5, 2) must_== 2
      }
    }

}
