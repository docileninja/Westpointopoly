package simulation.player.strategy

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.Player

/** An aggressive strategy that buys property when it has enough money.
  *
  * @param board a board to reference to the strategy
  */
class AggressiveStrategy(board: Board = Board()) extends Strategy(board) {

  /** Returns the decision to buy (true when has enough money). */
  def willBuy(player: Player): Boolean = {
    player.money > player.currentSpace.asInstanceOf[Property].cost
  }

}

/** A factory for [[simulation.player.strategy.AggressiveStrategy]]. */
object AggressiveStrategy {

  /** Returns an aggressive strategy for the given board. */
  def apply(board: Board = Board()) = new AggressiveStrategy(board)

}