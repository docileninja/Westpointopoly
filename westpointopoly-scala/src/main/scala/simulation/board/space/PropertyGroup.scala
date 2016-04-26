package simulation.board.space

import java.awt.Color

/** A property group that [[simulation.board.space.Property]]'s belong to.
  *
  * @param name a name for the property group
  * @param color a color for the property
  */
case class PropertyGroup(name: String, color: Color) {
    var properties = Set.empty[Property]
}