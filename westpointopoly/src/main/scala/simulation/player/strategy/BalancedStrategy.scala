package simulation.player.strategy

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.Player

/**
  * Created by x87039 on 3/3/2016.
  */
class BalancedStrategy(board: Board = Board()) extends Strategy(board) {
  def willBuy(player: Player): Boolean = {
    val currentSpace = player.currentSpace.asInstanceOf[Property]
    if (player.money > currentSpace.cost){ //if a player has enough money to buy the property
      for ( n <- player.properties){
        if (currentSpace.group == n.group) return true  //if  player has another property in the group buy
      }
      if (player.money - currentSpace.cost >= 50) return true //if the player does not have another prop in the group buy
      else return false // otherwise save enough money to traverse the board
    }
    else return false  //player cannot afford the property
  }
}

object BalancedStrategy {
  def apply(board: Board = Board()) = new BalancedStrategy(board)
}