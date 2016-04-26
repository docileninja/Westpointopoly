package controller

import simulation.board.Board
import view.BoardView

/** A view controller to manage a board view.
  *
  * @param board a board to be displayed.
  */
class BoardViewController(board: Board) extends ViewController {
  for (space <- board.spaces.reverse) {
    children ::= new SpaceViewController(space)
  }
  val view = new BoardView(this)

  /** Rerenders view. */
  override def updateView(): Unit = {
    view.repaint()
  }

}
