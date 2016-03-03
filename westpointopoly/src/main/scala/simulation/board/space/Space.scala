package simulation.board.space

/**
  * Created by adam on 1/30/16.
  */
class Space(name: String) {
  def abbreviation = name.filter(c => c <= 'Z' && c >= 'A')
  def abbr = abbreviation
  override def toString = name
}

object Space {
  def apply(name: String) = new Space(name)
}