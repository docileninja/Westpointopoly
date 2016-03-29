package simulation.player

import simulation.board.Board
import simulation.board.space.Property
import simulation.player.strategy.{DefaultStrategy, Strategy}

/** A player for the Westpointopoly board
  *
  * @param name the player's name
  * @param strategy the player's strategy
  * @param money the player's starting money
  * @param board implicit reference to the board
  */
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

  /** Returns the player's current space. */
  def currentSpace = board.spaces(location)

  /** Returns the player's last roll. */
  def lastRoll = rollHistory.head

  /** Returns whether or not the player should go to jail. */
  def shouldGoToJail = rollHistory.take(3).count(_ == lastRoll) == 3

  /** Pays another player a give amount in dollars.
    *
    * @param player the player to pay
    * @param dollars the amount of dollars to pay them
    */
  def payPlayer(player: Player, dollars: Int) = {
    val amount = dollars min money
    money -= dollars //want to indicate bankruptcy
    player.money += amount //give other player most money we can
  }

  /** Moves the player one turn.
    *
    * @param roll the roll to move the player by
    */
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

  /** Returns a string representation of the player. */
  override def toString = s"$name ($$$money) at ${currentSpace.abbr}"

  //hack
  location = 0
}