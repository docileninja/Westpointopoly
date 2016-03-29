package controller

import simulation.player.PlayerOrder
import view.PlayerView

/** A view controller to manage a player view.
  *
  * @param players a player order to be displayed.
  */
class PlayerViewController(val players: PlayerOrder) extends ViewController {

  val view = new PlayerView(this)

}
