package simulation.board.space

import java.awt.Color

import simulation.player.Player

/** A space on [[simulation.board.Board]]. */
class Space(val name: String) {

  var players = Set.empty[Player]

  /** Returns the abbreviation of the space made from all capital letters. */
  def abbreviation = name.filter(c => c <= 'Z' && c >= 'A')

  /** Synonym for abbreviation. */
  def abbr = abbreviation

  /** Returns name of the space. */
  override def toString = name

  /** Returns the color for the top of the space. */
  def color = Color.pink
}

/** Factory that returns [[simulation.board.space]]. */
object Space {

  /** Returns a new space with the given name.
    *
    * @param name the space's name
    * @return a new space
    */
  def apply(name: String) = new Space(name)

}