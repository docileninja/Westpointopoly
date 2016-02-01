package simulation.board

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adam on 1/26/16.
  */
class BoardTest extends FunSpec with Matchers {

  describe("Board holds most of the game's state") {

    describe("maintains an array of the 16 game spaces") {
      Board.spaces.size shouldBe 16

      it("should have 11 properties") {
        Board.spaces.count {
          case _: Property => true
          case _ => false
        } shouldBe 11
      }

    }

  }

}
