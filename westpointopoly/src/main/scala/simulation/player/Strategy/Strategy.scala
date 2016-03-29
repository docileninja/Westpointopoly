package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/** A strategy for a player to play Westpointopoly by.
  *
  * @param board a board to reference to the strategy
  */
abstract class Strategy(var board: Board) {

  /** Returns a decision for a player at the current point in the game.
    *
    * @param player a player making a decision
    * @return a decision
    */
  def willBuy(player: Player): Boolean

  /** Returns the strategy name. */
  def name = this.getClass.getSimpleName

}
