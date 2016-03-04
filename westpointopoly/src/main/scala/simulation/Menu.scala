package simulation

import simulation.board.Board
import simulation.player.Player
import simulation.player.strategy.Strategy

class Menu(board: Board) {
	def showGame = board.showGame
  def listPlayers = board.listPlayers
  def advanceTurn() = board.advanceTurn()
  def checkForWinner(): Option[String]  = board.winner
  def doMove() = board.doMove()
  def doTurn() = for (_ <- board.players) doMove()
  def doGame(callback: Unit => Unit) = {
    while (board.winner.isEmpty) {
      doMove()
      callback()
    }
  }
  def setPlayerStrategy(player: Player, stategy: Strategy): Unit = ???
  def showStrategies: Unit = ???
}

object Menu {
    def apply(board: Board) = new Menu(board)
}