package view

import controller.GameViewController
import scala.swing._
import BorderPanel.Position._

/**
  * Created by x87039 on 3/3/2016.
  */
class GameView(controller: GameViewController) extends MainFrame  {
  preferredSize = new Dimension(500, 800)
  contents = new BorderPanel {
    layout += controller.boardViewController.view -> North
    layout += controller.playerViewController.view -> South
  }
  menuBar = new MenuBar {
    contents += new Menu("Actions") {
      contents += new MenuItem(Action("Do move") {
        controller.doMove()
      })
      contents += new MenuItem(Action("Do turn") {
        controller.doTurn()
      })
      contents += new MenuItem(Action("Do game") {
        new Thread {
          override def run: Unit = {
            controller.doGame()
          }
        }.start
      })
    }
  }
  this.visible = true
}
