package simulation

import scala.sys

class Game {

	var players = List.empty[String]

	def showGame = {
		println("+-------------+")
		println("|_|_|_|_|_|_|_|")
		println("|_|         |_|")
		println("|_|         |_|")
		println("|_|_ _ _ _ _|_|")
		println("|_| | | | | |_|")
		println("+-------------+")
	}

	def listPlayers = println("Players: " + players.mkString(", "))

	def advanceTurn() = players = players.drop(1) :+ players.head

	def run() {
		players = List("Adam", "Matt", "Peter", "Lisa", "Andrew")
		while (true) {
			println("[1] Show game area")
			println("[2] Show player order")
			println("[3] Advance order")
			val choice = readInt()
			choice match {
				case 1 => showGame
				case 2 => listPlayers
				case 3 => advanceTurn()
				case _ => sys.exit()
			}
		}
	}

}