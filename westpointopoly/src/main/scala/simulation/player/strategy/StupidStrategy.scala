package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class StupidStrategy(board: Board = Board()) extends Strategy(board) {
  def willBuy(player: Player) = true
}

object StupidStrategy {
  def apply(board: Board = null) = new StupidStrategy(board)
}