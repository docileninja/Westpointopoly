package simulation.player

import simulation.board.Board
import simulation.player.strategy.{DefaultStrategy, Strategy}

import scala.collection.mutable.Queue

class PlayerOrder(val _players: Player*) extends Queue[Player] {
  this ++= _players

  override def toString = this.mkString(", ")
  def advance() = {
    val current = this.dequeue()
    if (current.money >= 0) this.enqueue(current)
  }
  def current = this.head
}

object PlayerOrder {
    def apply(players: Player*): PlayerOrder = new PlayerOrder(players:_*)
    def apply(players: String*)(implicit board: Board = Board()): Strategy => PlayerOrder = {
      def func(strategy: Strategy = DefaultStrategy(board)): PlayerOrder = {
        this(players.map { name: String => Player(name, strategy)(board)}:_*)
      }
      func(_)
    }
}