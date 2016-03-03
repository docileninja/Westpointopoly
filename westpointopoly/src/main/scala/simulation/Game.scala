package simulation

import scala.sys
import simulation.board.Board

class Game {
	
	val players = List("Adam", "Matt", "Peter", "Lisa", "Andrew")
	val board = Board(players)
	val menu = Menu(board)

	def run() {
		
		for (_ <- 1 to 4) {
			board.doMove()
			println(board.showGame)
		}
	}

}