package controller

import simulation.board.space.Space
import view.SpaceView

/**
  * Created by x87039 on 3/3/2016.
  */
class SpaceViewController(val space: Space) extends ViewController {
  val view = new SpaceView(this)
  override def updateView() = {
    view.repaint()
  }
}
