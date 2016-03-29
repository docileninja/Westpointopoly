package simulation.player

import simulation.board.Board
import simulation.player.strategy.{DefaultStrategy, Strategy}

import scala.collection.mutable.Queue

/** A player ordering that contains a queue of players.
  *
  * @param _players an initial list of player to put in the queue
  */
class PlayerOrder(val _players: Player*) extends Queue[Player] {
  this ++= _players

  /** Returns a string that represents the player order. */
  override def toString = this.mkString(", ")

  /** Advances the player order by one. */
  def advance() = {
    val current = this.dequeue()
    if (current.money >= 0) this.enqueue(current)
  }
  def current = this.head
}

/** Factory for [[simulation.player.PlayerOrder]]. */
object PlayerOrder {

  /** Returns a [[simulation.player.PlayerOrder]] with a list of players. */
  def apply(players: Player*): PlayerOrder = new PlayerOrder(players:_*)

  /** Returns a [[simulation.player.PlayerOrder]] with a list of players
    * with string of their names.
    */
  def apply(players: String*)(implicit board: Board = Board()): Strategy => PlayerOrder = {
    def func(strategy: Strategy = DefaultStrategy(board)): PlayerOrder = {
      this(players.map { name: String => Player(name, strategy)(board)}:_*)
    }
    func(_)
  }
}