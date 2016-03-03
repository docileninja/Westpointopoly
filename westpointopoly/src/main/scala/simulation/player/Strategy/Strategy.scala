package simulation.player.strategy

import simulation.board.Board
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
abstract class Strategy(board: Board) {
  def willBuy(player: Player): Boolean
}
