package view

import controller.{SpaceViewController, BoardViewController}

import scala.swing._

/** A parent view for board spaces
  *
  * @constructor create a new board view with a controller.
  * @param controller the view's controller
  */
class BoardView(controller: BoardViewController) extends GridPanel(5, 5) {

  preferredSize = new Dimension(500, 500)

  /** "fixture" creating new panel instances */
  private def panel = new Panel {}
  private def addNext(k: Int): Unit = {
    val spaceView = controller.children(k).asInstanceOf[SpaceViewController].view
    contents += spaceView
  }
  for (i <- 0 to 4) {
    addNext(i)
  }
  addNext(15)
  contents += panel
  contents += panel
  contents += panel
  addNext(5)
  addNext(14)
  contents += panel
  contents += panel
  contents += panel
  addNext(6)
  addNext(13)
  contents += panel
  contents += panel
  contents += panel
  addNext(7)
  for (i <- 12 to 8 by -1) {
    addNext(i)
  }

}
