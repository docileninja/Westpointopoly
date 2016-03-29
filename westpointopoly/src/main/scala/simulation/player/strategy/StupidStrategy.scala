package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/** A stupid strategy that buys property no matter what.
  *
  * @param board a board to reference to the strategy
  */
class StupidStrategy(board: Board = Board()) extends Strategy(board) {

  /** Returns the decision to buy (always true). */
  def willBuy(player: Player) = true

}

/** A factory for [[simulation.player.strategy.StupidStrategy]]. */
object StupidStrategy {

  /** Returns a stupid strategy for the given board. */
  def apply(board: Board = null) = new StupidStrategy(board)

}