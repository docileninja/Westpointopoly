package simulation.player.strategy

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class AggressiveStrategy(board: Board = null) extends Strategy(board) {
  def willBuy(player: Player): Boolean = {
    player.money > player.currentSpace.asInstanceOf[Property].cost
  }
}

object AggressiveStrategy {
  def apply(board: Board = Board()) = new AggressiveStrategy(board)
}