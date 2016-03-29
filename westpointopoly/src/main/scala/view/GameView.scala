package view

import controller.GameViewController
import scala.swing._
import BorderPanel.Position._

/** A parent view for all game information
  *
  * @constructor create a new game view with a controller.
  * @param controller the view's controller
  */
class GameView(controller: GameViewController) extends MainFrame  {
  preferredSize = new Dimension(500, 700)
  resizable = false
  title = "Westpointopoly"
  contents = new BoxPanel(Orientation.Vertical) {
    contents += controller.boardViewController.view
    contents += controller.playerViewController.view
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
      contents += new MenuItem(Action("Show strategies") {
        Dialog.showMessage(this, controller.menu.showStrategies, title="Strategies")
      })
    }
    for (player <- controller.board.players) {
      contents += new Menu(player.name) {
        for (strategy <- controller.board.strategies) {
          contents += new MenuItem(Action(strategy.name) {
            controller.menu.setPlayerStrategy(player, strategy)
          })
        }
      }
    }
  }
  this.visible = true
}
