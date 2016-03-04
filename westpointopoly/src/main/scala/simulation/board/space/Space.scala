package simulation.board.space

import simulation.player.Player

/**
  * Created by adam on 1/30/16.
  */
class Space(name: String) {
  def abbreviation = name.filter(c => c <= 'Z' && c >= 'A')
  def abbr = abbreviation
  var players = Set.empty[Player]
  override def toString = name
}

object Space {
  def apply(name: String) = new Space(name)
}