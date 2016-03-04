package view

import java.awt.Color

import controller.SpaceViewController
import simulation.board.space.{Property, Space}

import scala.swing._

/**
  * Created by x87039 on 3/3/2016.
  */
class SpaceView(controller: SpaceViewController) extends Panel {
  val (w, h) = (200, 200)
  preferredSize = new Dimension(w, h)

  override def paint(gr: Graphics2D) = {
    val decorator = controller.space match {
      case property: Property => {
        var s = ""
        property.owner match {
          case Some(player) => s = "Owned by " + player.name
          case None => s = "No owner"
        }
        val (r, g, b) = property.group.color
        gr.setColor(new Color(r, g, b))
        s
      }
      case _ => {gr.setColor(Color.pink); ""}
    }
    gr.fillRect(0, 0, w, h)
    gr.setColor(Color.black)
    gr.setFont(new Font("Arial", 0, 15))
    gr.drawString(controller.space.toString, 5, h/2)
    gr.drawString(decorator, 5, h/2 - 20)
    for ((player, i) <- controller.space.players.zipWithIndex) {
      gr.drawString(player.toString, 5, h / 2 + 30 + i * 20)
    }
  }
}
