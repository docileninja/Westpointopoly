package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class BalancedStrategy(board: Board = Board()) extends Strategy(board) {
  def willBuy(player: Player): Boolean = ???
}

object BalancedStrategy {
  def apply(board: Board = Board()) = new BalancedStrategy(board)
}