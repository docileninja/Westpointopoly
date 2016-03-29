package controller

import simulation.board.space.Space
import view.SpaceView

/** A view controller to manage a space view.
  *
  * @param space a space to be displayed.
  */
class SpaceViewController(val space: Space) extends ViewController {

  val view = new SpaceView(this)

  /** Rerenders the space view. */
  override def updateView() = {
    view.repaint()
  }
}
