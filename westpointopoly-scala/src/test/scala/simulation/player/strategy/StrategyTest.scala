package simulation.player.strategy

import org.scalatest.{FunSpec, Matchers}
import simulation.board.{Dice, Board}
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class StrategyTest extends FunSpec with Matchers {

  describe("Different strategies determine player behavior when landing on a space") {
    val rolls = List((1,4),(0,1),(0,1),(0,2),(2,3),(1,1))

    it("Has a Default Strategy that buys whenever it has enough money") {
      val player: Player = Player("D", money = 420)
      val players = List(player)
      val board = Board(players, dice = Dice(rolls.toIterator))
      val strategy = DefaultStrategy(board)
      player.strategy = strategy

      player.strategy shouldBe strategy

      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.thayerHall, board.hayes)
    }

    it("Has an Aggressive Strategy") {
      val player: Player = Player("D", money = 420)
      val players = List(player)
      val board = Board(players, dice = Dice(rolls.toIterator))
      val strategy = AggressiveStrategy(board)
      player.strategy = strategy

      player.strategy shouldBe strategy

      board.doMove()
      board.players.current.properties shouldBe Set(board.jeffersonHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
    }

    it("Has a Balanced Strategy") {
      val player: Player = Player("D", money = 420)
      val players = List(player)
      val board = Board(players, dice = Dice(rolls.toIterator))
      val strategy = BalancedStrategy(board)
      player.strategy = strategy

      player.strategy shouldBe strategy

      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
    }

    it("Has a Stupid Strategy") {
      val player: Player = Player("D", money = 420)
      val players = List(player)
      val board = Board(players, dice = Dice(rolls.toIterator))
      val strategy = StupidStrategy(board)
      player.strategy = strategy

      player.strategy shouldBe strategy

      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall, board.messHall)
      board.doMove()
      player.properties shouldBe Set(board.jeffersonHall, board.bartlettHall, board.thayerHall, board.messHall, board.hayes)
      player.money shouldBe -100
    }
  }
}
