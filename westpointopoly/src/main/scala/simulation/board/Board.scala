package simulation.board

import java.awt.Color

import simulation.board.space.{Space, PropertyGroup, Property}
import simulation.player.strategy.DefaultStrategy
import simulation.player.{Player, PlayerOrder}

/** A board that Westpointopoly is played on.
  *
  * @param playerNames the names of players to be created
  * @param dice a dice to be used for the rolls
  */
class Board(playerNames: Seq[String], dice: Dice = Dice()) {

  val restaruants = PropertyGroup("Restaurants", Color.red)
  val fitness = PropertyGroup("Halls of Iron", Color.gray)
  val academics = PropertyGroup("Academics", Color.green)
  val stores = PropertyGroup("Stores", Color.yellow)

  val grantHall = Property("Grant Hall", 200, restaruants)
  val messHall = Property("Mess Hall", 50, restaruants)
  val theFirstie = Property("The Firstie", 120, restaruants)

  val arvin = Property("Arvin", 660, fitness)
  val hayes = Property("Hayes Gymnasium", 100, fitness)

  val thayerHall = Property("Thayer Hall", 150, academics)
  val bartlettHall = Property("Bartlett Hall", 120, academics)
  val jeffersonHall = Property("Jefferson Hall", 100, academics)

  val cadetStore = Property("C-store", 100, stores)
  val thayerBookstore = Property("Thayer Bookstore", 50, stores)
  val companyStore = Property("Company Store", 80, stores)

  val propertyGroups = Set(restaruants, fitness, academics, stores)
  val properties = Set(grantHall, messHall, theFirstie, arvin, hayes, thayerHall, bartlettHall, jeffersonHall, cadetStore, thayerBookstore, companyStore)

  val spaces = Array(Space("GO"), thayerBookstore, cadetStore, companyStore, Space("Hours")) ++
    Array(jeffersonHall, bartlettHall, thayerHall, Space("DCA")) ++
    Array(messHall, grantHall, theFirstie, Space("Go to Hours"), Space("Tasking"), hayes, arvin)

  implicit val board = this // needed for the construction of new players
  val players = PlayerOrder(playerNames:_*)(board)(new DefaultStrategy(this))

  /** Returns a full string representation of board state including player info. */
  def showGame = show + listPlayers + "\n\n" + listProperties

  /** Returns a string representation of the board. */
  def show = {
    val ns = this.spaces.map(_.abbr)
    "+----+----+----+----+----+\n" +
      "| %2s | %2s | %2s | %2s | %2s |\n".format(ns(0), ns(1), ns(2), ns(3), ns(4)) +
      "+----+----+----+----+----+\n" +
      "| %2s | P  Stores    | %2s |\n".format(ns(15), ns(5)) +
      "+----+ h          E +----+\n" +
      "| %2s | y          d | %2s |\n".format(ns(14), ns(6)) +
      "+----+ s          u +----+\n" +
      "| %2s |  Restaurants | %2s |\n".format(ns(13), ns(7)) +
      "+----+----+----+----+----+\n" +
      "| %2s | %2s | %2s | %2s | %3s|\n".format(ns(12), ns(11), ns(10), ns(9), ns(8)) +
      "+----+----+----+----+----+\n"
  }

  /** Returns a string representation of the players. */
	def listPlayers = "Players: " + players.toString

  /** Returns a string representation of the players' properties. */
	def listProperties = players.map { p: Player => p + "\n" + p.properties.mkString("\n") + "\n"}.mkString("\n")

  /** Advances the players without moving. */
	def advanceTurn() = players.advance()

  /** Returns Some(winner) or None if there is no winner. */
  def winner: Option[String] = if (players.size == 1) Some(players.head.name) else None

  /** Does a move for the current player. */
  def doMove() = {
    players.current.move(dice.roll)
    advanceTurn()
  }
	
}

/** Factory for [[simulation.board.Board]]. */
object Board {

  /** Returns a new board based on the names and dice.
    *
    * @param playerNames a list of player names to create
    * @param dice a dice for the board to use
    * @return a new board
    */
  def apply(playerNames: Seq[String] = Seq.empty[String], dice: Dice = Dice()) = new Board(playerNames, dice)

  /** Return a new board with no players.
    *
    * @return a new board
    */
  def apply() = new Board(Seq.empty[String], Dice())

}