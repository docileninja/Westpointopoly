package controller

import simulation.Menu
import simulation.board.{Dice, Board}
import view.{SpaceView, GameView}

import scala.swing.BorderPanel
import BorderPanel.Position._

/**
  * Created by x87039 on 3/3/2016.
  */
class GameViewController extends ViewController {
  val board = Board(List("P1", "P2", "P3", "P4"), Dice(3))
  val boardViewController = new BoardViewController(board)
  val playerViewController = new PlayerViewController(board.players)
  children ++= List(boardViewController, playerViewController)
  val view = new GameView(this)

  val menu = Menu(board)

  def doMove(): Unit = {
    menu.doMove()
    update()
  }

  def doTurn(): Unit = {
    menu.doTurn()
    update()
  }

  def doGame(): Unit = {
    menu.doGame((Unit) => {
      update()
      Thread.sleep(1000)
    })
  }

  override def updateView(): Unit = {
    view.repaint()
  }
}
