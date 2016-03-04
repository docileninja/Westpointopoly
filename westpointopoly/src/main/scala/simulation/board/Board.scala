package simulation.board

import simulation.board.space.{Space, PropertyGroup, Property}
import simulation.player.strategy.DefaultStrategy
import simulation.player.{Player, PlayerOrder}

class Board(playerNames: Seq[String], dice: Dice = Dice()) {

  val restaruants = PropertyGroup("Restaurants", (255, 0, 0))
  val fitness = PropertyGroup("Halls of Iron", (128, 128, 128))
  val academics = PropertyGroup("Academics", (0, 255, 0))
  val stores = PropertyGroup("Stores", (0, 128, 128))

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

  val propertyGroups = Set(restaruants, fitness, academics, stores, auditoriums)
  val properties = Set(grantHall, messHall, theFirstie, arvin, hayes, thayerHall, bartlettHall, jeffersonHall, cadetStore, thayerBookstore, companyStore)

  val spaces = Array(Space("GO"), thayerBookstore, cadetStore, companyStore, Space("Hours")) ++
    Array(jeffersonHall, bartlettHall, thayerHall, Space("DCA")) ++
    Array(messHall, grantHall, theFirstie, Space("Go to Hours"), Space("Tasking"), hayes, arvin)

  implicit val board = this
  val players = PlayerOrder(playerNames:_*)(board)(new DefaultStrategy(this))

  def showGame = show + listPlayers + "\n\n" + listProperties

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
	
	def listPlayers = "Players: " + players.toString

	def listProperties = players.map { p: Player => p + "\n" + p.properties.mkString("\n") + "\n"}.mkString("\n")

	def advanceTurn() = players.advance()
  def winner: Option[String] = if (players.size == 1) Some(players.head.name) else None
  def doMove() = {
    players.current.move(dice.roll)
    advanceTurn()
  }
	
}

object Board {
  def apply(playerNames: Seq[String], dice: Dice = Dice()) = new Board(playerNames, dice)
  def apply() = new Board(Seq.empty[String])
}