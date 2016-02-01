package simulation.player

import simulation.board.{Board, Property}

case class Player(val name: String, var money: Int = 500) {
    var properties = Set.empty[Property]
    var location = 0
    var rollHistory = List.empty[(Int, Int)]

    def lastRoll = rollHistory.head
    def shouldGoToJail = rollHistory.take(3).count(_ == lastRoll) == 3
    def roll() = {

    }
    override def toString = s"$name ($$$money) at ${Board.spaces(location)}"
}