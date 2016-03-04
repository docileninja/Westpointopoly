package simulation.player

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.strategy.{DefaultStrategy, Strategy}

case class Player(val name: String, var strategy: Strategy = DefaultStrategy(), var money: Int = 500)(implicit board: Board = Board()) {
  var properties = Set.empty[Property]
  var rollHistory = List.empty[(Int, Int)]
  var _location = 0
    def location = _location
    def location_=(n: Int) = {
      if (n >= board.spaces.size) money += 200
      currentSpace.players -= this
      _location = n % board.spaces.size
      currentSpace.players += this
    }
  def currentSpace = board.spaces(location)
  def lastRoll = rollHistory.head
  def shouldGoToJail = rollHistory.take(3).count(_ == lastRoll) == 3

  def payPlayer(player: Player, dollars: Int) = {
    val amount = dollars min money
    money -= dollars //want to indicate bankruptcy
    player.money += amount //give other player most money we can
  }

  def move(roll: (Int, Int)) = {
    val (d1, d2) = roll
    location += d1 + d2
    currentSpace match {
      case property: Property => {
        property.owner match {
          case None => {
            if (strategy.willBuy(this)) {
              property.owner = Some(this)
              this.money -= property.cost
              this.properties += property
            }
          }
          case Some(owner) => {
            if (this != owner) {
              payPlayer(owner, property.rent)
            }
          }
        }
      }
      case _ => {}
    }
  }
  override def toString = s"$name ($$$money) at ${currentSpace.abbr}"

  //hack
  location = 0
}