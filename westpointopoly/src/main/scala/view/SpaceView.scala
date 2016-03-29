package view

import java.awt.{BasicStroke, Color}

import controller.SpaceViewController
import simulation.board.space.{Property, Space}

import scala.swing._

/** A view for a specific space's information
  *
  * @constructor create a new space view with a controller.
  * @param controller the view's controller
  */
class SpaceView(controller: SpaceViewController, dim: Int = 100) extends Panel {
  var (w, h) = (dim, dim)
  val barHeight = h/5
  preferredSize = new Dimension(w, h)

  /** Renders the board space in the view. */
  override def paint(gr: Graphics2D) = {
    val decorator = controller.space match {
      case property: Property => s"$$${property.cost}"
      case _ => ""
    }
    implicit val g = gr
    val space = controller.space
    gr.setColor(new Color(205, 230, 208))
    gr.fillRect(0, 0, w, h)
    gr.setColor(space.color)
    gr.fillRect(0, 0, w, barHeight)
    gr.setColor(Color.black)
    gr.setStroke(new BasicStroke())
    Draw.stringCenter(space.name, 15, w, barHeight + dim / 10)
    Draw.stringCenter(decorator, 15, w, h - dim / 10)
    for ((player, i) <- space.players.zipWithIndex) {
      Draw.stringCenter(player.toString, 15, w, h / 2 + i * dim / 10)
    }
  }
}
