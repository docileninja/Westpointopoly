package simulation

import scala.sys

class Game {

	var players = List.empty[String]

	def showGame = {
		var board = ""
		board += "+-------------+\n"
		board += "|_|_|_|_|_|_|_|\n"
		board += "|_|         |_|\n"
		board += "|_|         |_|\n"
		board += "|_|_ _ _ _ _|_|\n"
		board += "|_| | | | | |_|\n"
		board += "+-------------+"
		board
	}

	def listPlayers = "Players: " + players.mkString(", ")

	def advanceTurn() = players = players.drop(1) :+ players.head

	def run() {
		players = List("Adam", "Matt", "Peter", "Lisa", "Andrew")
		while (true) {
			println("[1] Show game area")
			println("[2] Show player order")
			println("[3] Advance order")
			val choice = readInt()
			choice match {
				case 1 => println(showGame)
				case 2 => println(listPlayers)
				case 3 => advanceTurn()
				case _ => sys.exit()
			}
		}
	}

}