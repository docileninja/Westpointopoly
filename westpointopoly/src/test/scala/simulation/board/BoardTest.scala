package simulation.board

import org.scalatest.{FunSpec, Matchers}
import simulation.board.space.Property

/**
  * Created by adam on 1/26/16.
  */
class BoardTest extends FunSpec with Matchers {

  describe("Board holds most of the game's state") {

    describe("maintains an array of the 16 game spaces") {
      val board = Board()
      board.spaces.size shouldBe 16

      it("should have 11 properties") {
        board.spaces.count {
          case _: Property => true
          case _ => false
        } shouldBe 11
      }

    }

  }

}
