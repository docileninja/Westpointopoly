package simulation

import scala.collection.mutable.Queue

class PlayerOrder extends Queue[Player] {
    def toString = this.mkString(", ")
    def advance = this.enqueue(this.dequeue())
}

object PlayerOrder {
    def apply(players: Seq[Player]) = new PlayerOrder(players:_*)
}