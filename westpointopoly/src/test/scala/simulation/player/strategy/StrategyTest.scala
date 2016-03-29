package simulation.player.strategy

import org.scalatest.{FunSpec, Matchers}
import simulation.board.{Dice, Board}
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class StrategyTest extends FunSpec with Matchers {

  describe("Different strategies determine player behavior when landing on a space") {
    val rolls = List((1,3),(0,1),(0,1),(0,2),(2,3),(1,1))

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
      player.properties shouldBe Set(board.jeffersonHall, board.thayerHall, board.theFirstie)
    }

    it("Has an Aggressive Strategy") {
      implicit val board = Board(dice= Dice(rolls.toIterator))
      val strategy = AggressiveStrategy()
      val player = Player("A", strategy, money = 420)
      board.players.enqueue(player)
      player.strategy shouldBe strategy

      board.doMove()
      board.players.current.properties shouldBe Set(board.jeffersonHall)
      println(board.players.current.properties)
      board.doMove()
      println(board.players.current.properties)
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall")
      board.doMove()
      println(board.players.current.properties)
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall")
      board.doMove()
      println(board.players.current.properties)
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall","Mess Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall","Mess Hall")
      println(board.players.current.properties)
    }

    it("Has a Balanced Strategy") {
      implicit val board = Board(dice= Dice(rolls.toIterator))
      val strategy = BalancedStrategy()
      val player = Player("B",strategy, money = 420)
      board.players.enqueue(player)
      player.strategy shouldBe strategy

      board.doMove()
      player.properties shouldBe Set("Jefferson Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall")
    }

    it("Has a Stupid Strategy") {
      implicit val board = Board(dice = Dice(rolls.toIterator))
      val strategy = StupidStrategy()
      val player = Player("S", strategy, money = 420)
      board.players.enqueue(player)
      player.strategy shouldBe strategy

      board.doMove()
      player.properties shouldBe Set("Jefferson Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall","Mess Hall")
      board.doMove()
      player.properties shouldBe Set("Jefferson Hall","Bartlett Hall","Thayer Hall","Mess Hall","The Firstie")
      player.money shouldBe -120
    }
  }
}
