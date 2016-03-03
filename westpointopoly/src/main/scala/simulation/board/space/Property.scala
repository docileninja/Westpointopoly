package simulation.board.space

import simulation.player.Player

case class Property(name: String, cost: Int, group: PropertyGroup) extends Space(name) {
  group.properties += this

  var owner: Option[Player] = None

  def rent = cost / 2
  override def toString = s"$name - $$$cost"
}