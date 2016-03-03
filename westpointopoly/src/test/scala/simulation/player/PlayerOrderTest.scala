package simulation.player

import org.scalatest.{FunSpec, Matchers}
import simulation.board.Board
import simulation.player.strategy.DefaultStrategy

/**
  * Created by adam on 1/26/16.
  */
class PlayerOrderTest extends FunSpec with Matchers {

  describe("PlayerOrder is a queue that keeps track of the player order and current player.") {
    it("should create new players from names") {
      val names = List("a", "b", "c")
      val playerOrder = PlayerOrder(names:_*)(Board())(DefaultStrategy())

      playerOrder.toString shouldBe "a ($500) at GO, b ($500) at GO, c ($500) at GO"
    }

    it("should also create from list of players") {
      val players = List(Player("a"), Player("b"), Player("c"))
      val playerOrder = PlayerOrder(players:_*)

      playerOrder.toString shouldBe "a ($500) at GO, b ($500) at GO, c ($500) at GO"
    }

    it("should display the first player and advance players") {
      val names = List("a", "b", "c")
      val playerOrder = PlayerOrder(names:_*)(Board())(DefaultStrategy())

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
