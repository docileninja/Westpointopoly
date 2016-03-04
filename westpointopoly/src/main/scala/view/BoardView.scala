package view

import controller.{SpaceViewController, BoardViewController}

import scala.swing._

/**
  * Created by x87039 on 3/3/2016.
  */
class BoardView(controller: BoardViewController) extends GridPanel(5, 5) {
  def panel = new Panel {}
  def addNext(k: Int): Unit = {
    val spaceView = new SpaceView(controller.children(k).asInstanceOf[SpaceViewController])
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
