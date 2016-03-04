package controller

import simulation.player.PlayerOrder
import view.PlayerView

/**
  * Created by x87039 on 3/3/2016.
  */
class PlayerViewController(players: PlayerOrder) extends ViewController {

  val view = new PlayerView()

}
