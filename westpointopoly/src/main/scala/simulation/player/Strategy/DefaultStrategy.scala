package simulation.player.strategy

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class DefaultStrategy(board: Board = Board()) extends Strategy(board) {
  def willBuy(player: Player) = {
    player.currentSpace.asInstanceOf[Property].cost < player.money
  }
}

object DefaultStrategy {
  def apply(board: Board = Board()) = new DefaultStrategy(board)
}