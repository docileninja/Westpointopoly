package simulation.player.strategy

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.Player

/** A balanced strategy that buys every other property it can.
  *
  * @param board a board to reference to the strategy
  */
class DefaultStrategy(board: Board = Board()) extends Strategy(board) {

  var buy = 1

  /** Returns the decision to buy (every other property). */
  def willBuy(player: Player): Boolean = {
    if (player.currentSpace.asInstanceOf[Property].cost <= player.money){
      if (buy == 1) {
        buy = 0
        return true
      }
      else {
        buy = 1
        return false
      }
    }
    return false
  }

}

/** A factory for [[simulation.player.strategy.DefaultStrategy]]. */
object DefaultStrategy {

  /** Returns a default strategy for the given board. */
  def apply(board: Board = Board()) = new DefaultStrategy(board)

}