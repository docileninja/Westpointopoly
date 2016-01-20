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
		it("must display player list properly") {
			val game = new simulation.Game()
			game.players = List("a", "b", "c")

			game.listPlayers shouldBe "Players: a, b, c"
			game.advanceTurn()
			game.listPlayers shouldBe "Players: b, c, a"
			game.advanceTurn()
			game.listPlayers shouldBe "Players: c, a, b"
		}
		it("must properly display the game board") {
			val game = new simulation.Game()
			game.showGame shouldBe "+-------------+\n|_|_|_|_|_|_|_|\n|_|         |_|\n|_|         |_|\n|_|_ _ _ _ _|_|\n|_| | | | | |_|\n+-------------+"
		}
	}
}