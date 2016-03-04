package controller

import simulation.board.Board
import view.BoardView

/**
  * Created by x87039 on 3/3/2016.
  */
class BoardViewController(board: Board) extends ViewController {
  for (space <- board.spaces.reverse) {
    children ::= new SpaceViewController(space)
  }
  val view = new BoardView(this)
  override def updateView(): Unit = {
    view.repaint()
  }
}
