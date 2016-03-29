package controller

import simulation.Menu
import simulation.board.{Dice, Board}
import view.{SpaceView, GameView}

import scala.swing.BorderPanel
import BorderPanel.Position._

/** A view controller to manage a game view. */
class GameViewController extends ViewController {

  val board = Board(List("P1", "P2", "P3", "P4"), Dice(3))
  val boardViewController = new BoardViewController(board)
  val playerViewController = new PlayerViewController(board.players)
  children ++= List(boardViewController, playerViewController)
  val view = new GameView(this)
  update()

  val menu = Menu(board)

  /** Does one move. */
  def doMove(): Unit = {
    menu.doMove()
    update()
  }

  /** Does one turn (one move for each player). */
  def doTurn(): Unit = {
    menu.doTurn()
    update()
  }

  /** Does moves until the game is over. */
  def doGame(): Unit = {
    menu.doGame((Unit) => {
      update()
      Thread.sleep(1000)
    })
  }

  /** Redraws view. */
  override def updateView(): Unit = {
    view.repaint()
  }

}
