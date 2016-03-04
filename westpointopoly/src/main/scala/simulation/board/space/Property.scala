package simulation.board.space

import simulation.player.Player

case class Property(_name: String, cost: Int, group: PropertyGroup) extends Space(_name) {
  group.properties += this

  var owner: Option[Player] = None

  def rent = cost / 2
  override def toString = s"$name - $$$cost"
  override def color = group.color
}