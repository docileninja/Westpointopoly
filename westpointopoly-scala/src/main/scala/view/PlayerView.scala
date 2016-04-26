package view

import java.awt.Color

import controller.PlayerViewController
import simulation.player.Player

import scala.swing._

/** A view for player information
  *
  * @constructor create a new player view with a controller.
  * @param controller the view's controller
  */
class PlayerView(controller: PlayerViewController) extends Panel {

  preferredSize = new Dimension(500, 200)

  /** Renders player view. */
  override def paint(g: Graphics2D): Unit = {
    g.drawString("Player order: " + controller.players, 20, 20)
    for ((player, i) <- controller.players.toList.sortBy(_.name).zipWithIndex) {
      g.drawString(player.toString, 5 + i * 130, 50)
      for ((property, j) <- player.properties.toList.zipWithIndex) {
        g.drawString(property.toString, 5 + i * 130, 65 + j * 15)
      }
    }
  }

}
