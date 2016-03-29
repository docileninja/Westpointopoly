package simulation

import simulation.board.Board
import simulation.player.Player
import simulation.player.strategy.Strategy

/** A menu that controls and provides a consistent interface to a
  * [[simulation.board.Board]].
  *
  * @param board the menu's managed board
  */
class Menu(board: Board) {

  /** Returns a string representing the board state. */
	def showGame = board.showGame

  /** Returns a string that lists players in order and basic info. */
  def listPlayers = board.listPlayers

  /** Moves the players forward one turn without performing move. */
  def advanceTurn() = board.advanceTurn()

  /** Returns an optional containing a player name or None if there is no winner. */
  def checkForWinner(): Option[String]  = board.winner

  /** Moves one player one move. */
  def doMove() = board.doMove()

  /** Moves all players one move. */
  def doTurn() = for (_ <- board.players) doMove()

  /** Moves players until there is a winner.
    *
    * @param callback provides a way perform actions after each turn in the UI
    */
  def doGame(callback: Unit => Unit) = {
    while (board.winner.isEmpty) {
      doMove()
      callback()
    }
  }

  /** Changes the strategy of a given player.
    *
    * @param player the player that will have its strategy changed
    * @param strategy the new strategy
    */
  def setPlayerStrategy(player: Player, strategy: Strategy): Unit = {
    board.players.find(_ == player).get.strategy = strategy
  }

  /** Returns a string representing the current strategies for the players. */
  def showStrategies: String = board.players.map((p: Player) => s"${p.name} - ${p.strategy.name}").mkString(", ")

}

/** Factory for [[simulation.Menu]]. */
object Menu {

  /** Returns a new menu for a given board.
    *
    * @param board a board for the menu to control
    * @return a new menu with a given board
    */
  def apply(board: Board) = new Menu(board)

}