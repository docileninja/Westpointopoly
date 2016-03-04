package simulation.board.space

import java.awt.Color

case class PropertyGroup(name: String, color: Color) {
    var properties = Set.empty[Property]
}