package controller

/**
  * Created by x87039 on 3/3/2016.
  */
abstract class ViewController {
  var children = List.empty[ViewController]
  def update(): Unit = {
    for (child <- children) child.update()
    updateView()
  }
  def updateView(): Unit = {}
}
