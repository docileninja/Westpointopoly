package simulation.player

import scala.collection.mutable.Queue

class PlayerOrder(val _players: Player*) extends Queue[Player] {
  this ++= _players

  override def toString = this.mkString(", ")
  def advance() = this.enqueue(this.dequeue())
  def current = this.head
}

object PlayerOrder {
    def apply(players: Player*): PlayerOrder = new PlayerOrder(players:_*)
    def apply(players: String*)(implicit d: DummyImplicit): PlayerOrder = this(players.map {name: String => Player(name)}:_*)
}