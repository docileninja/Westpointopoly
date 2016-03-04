package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class AggressiveStrategy(board: Board = Board()) extends Strategy(board) {
  def willBuy(player: Player): Boolean = ???
}

object AggressiveStrategy {
  def apply(board: Board = Board()) = new AggressiveStrategy(board)
}