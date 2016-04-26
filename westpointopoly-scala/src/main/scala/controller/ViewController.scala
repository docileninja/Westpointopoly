package controller

/** An abstract view controller that other view controllers inherit from */
abstract class ViewController {

  var children = List.empty[ViewController]

  /** Updates view controller's view and views of all child view controllers recursively */
  def update(): Unit = {
    for (child <- children) child.update()
    updateView()
  }

  /** A method to override for update the view controller and its view */
  def updateView(): Unit = {}

}
