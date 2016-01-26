package simulation

import scala.sys

class Game {
	
	val players = List("Adam", "Matt", "Peter", "Lisa", "Andrew")
	val board = Board(players)
	val menu = Menu(board)

	def run() {
		
		while (true) {
			println("[1] Show game area")
			println("[2] Show player order")
			println("[3] Advance order")
			val choice = readInt()
			choice match {
				case 1 => println(menu.showGame)
				case 2 => println(menu.listPlayers)
				case 3 => menu.advanceTurn()
				case _ => sys.exit()
			}
		}
	}

}