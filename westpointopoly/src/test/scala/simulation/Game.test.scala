package simulation

import org.scalatest.{FunSpec, Matchers}

class GameTest extends FunSpec with Matchers {

	describe("Game provides basic game state management") {
		it("must advance players properly") {
			val game = new simulation.Game()
			game.players = List("a", "b", "c")

			game.advanceTurn()
			game.players shouldBe List("b", "c", "a")
			game.advanceTurn()
			game.players shouldBe List("c", "a", "b")
			game.advanceTurn()
			game.players shouldBe List("a", "b", "c")
		}
	}

}