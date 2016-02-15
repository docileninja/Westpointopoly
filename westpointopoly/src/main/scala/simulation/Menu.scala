package simulation

import simulation.board.Board

class Menu(board: Board) {

	def showGame = board.showGame
  def listPlayers = board.listPlayers
  def advanceTurn() = board.advanceTurn()
  def doMove() = ???
  def doTurn() = ???
	
}

object Menu {
    def apply(board: Board) = new Menu(board)
}