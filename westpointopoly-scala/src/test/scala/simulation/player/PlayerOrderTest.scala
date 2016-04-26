package simulation.player

import org.scalatest.{FunSpec, Matchers}
import simulation.board.Board
import simulation.player.strategy.DefaultStrategy

/**
  * Created by adam on 1/26/16.
  */
class PlayerOrderTest extends FunSpec with Matchers {

  describe("PlayerOrder is a queue that keeps track of the player order and current player.") {

    def players = {
      val board = Board()
      List("a", "b", "c").map(Player(_, board=board))
    }

    it("should also create from list of players") {
      val playerOrder = PlayerOrder(players:_*)

      playerOrder.toString shouldBe "a ($500) at GO, b ($500) at GO, c ($500) at GO"
    }

    it("should display the first player and advance players") {
      val playerOrder = PlayerOrder(players:_*)

      playerOrder.current.name shouldBe "a"
      playerOrder.advance()
      playerOrder.current.name shouldBe "b"
      playerOrder.advance()
      playerOrder.current.name shouldBe "c"
      playerOrder.advance()
      playerOrder.current.name shouldBe "a"
    }
  }

}
