package simulation.board.space

import simulation.player.Player

/** A property that players can own for a cost.
  *
  * @param _name the property's space
  * @param cost the property's cost
  * @param group the property's [[simulation.board.space.PropertyGroup]]
  */
case class Property(_name: String, cost: Int, group: PropertyGroup) extends Space(_name) {
  group.properties += this

  var owner: Option[Player] = None

  /** Returns the rent for the property. */
  def rent = cost / 2

  /** Returns a string representation for the property. */
  override def toString = s"$name - $$$cost"

  /** Returns the color of the property group. */
  override def color = group.color

}