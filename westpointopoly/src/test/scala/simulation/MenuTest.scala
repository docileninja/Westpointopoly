package simulation

import org.scalatest.{FunSpec, Matchers}
import simulation.board.Board

/**
  * Created by adam on 1/26/16.
  */
class MenuTest extends FunSpec with Matchers {

  describe("Menu commands the board through the game instance") {

    it("can show the board") {
      val board = new Board(List("Adam", "Kenny"))
      val menu = Menu(board)
      val expected =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Adam ($500) at GO
          |
          |
          |Kenny ($500) at GO
          |
          |
          |""".stripMargin
      menu.showGame shouldBe expected
    }

    it("can show player order") {
      val board = new Board(List("Adam", "Kenny"))
      val menu = Menu(board)

      menu.listPlayers shouldBe "Players: Adam ($500) at GO, Kenny ($500) at GO"
    }

    it("can advance players") {
      val board = new Board(List("Adam", "Kenny"))
      val menu = Menu(board)

      menu.listPlayers shouldBe "Players: Adam ($500) at GO, Kenny ($500) at GO"
      menu.advanceTurn()
      menu.listPlayers shouldBe "Players: Kenny ($500) at GO, Adam ($500) at GO"
      menu.advanceTurn()
      menu.listPlayers shouldBe "Players: Adam ($500) at GO, Kenny ($500) at GO"
    }

    it("can initialize the game") {
      val board = new Board(List("P1", "P2", "P3", "P4"))
      val menu = Menu(board)

      menu.initializeGame

      for (player <- board.players) {
        player.money shouldBe 500
        player.location shouldBe 0
        player.rollHistory.size shouldBe 0
        player.properties.size shouldBe 0
      }



    }

    it("can check for winner") {
      val board = new Board(List("P1", "P2", "P3", "P4"))

      board.winner shouldBe None

      val winBoard = new Board(List("P1"))

      board.winner shouldBe Some("P1")
    }


    // For the sake of testing we are fixing the rolls of the players IOT have all players land
    // on the same space, making the game as short as possible.

    it("can do a move") {
      val board = new Board(List("P1", "P2", "P3", "P4"))
      val menu = Menu(board)

      val beginning =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P1 ($500) at GO, P2 ($500) at GO, P3 ($500) at GO, P4 ($500) at GO
          |
          |P1 ($500) at GO
          |
          |
          |P2 ($500) at GO
          |
          |
          |P3 ($500) at GO
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin

      val promptForPurchase =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Would you like to buy CS? (Y/N)
          |Players: P1 ($500) at GO, P2 ($500) at GO, P3 ($500) at GO, P4 ($500) at GO
          |
          |P1 ($500) at CS
          |
          |
          |P2 ($500) at GO
          |
          |
          |P3 ($500) at GO
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin

      menu.showGame shouldBe beginning
      menu.doMove()
      menu.showGame shouldBe promptForPurchase

    }

    it("can do a turn") {
      val board = new Board(List("P1", "P2", "P3", "P4"))
      val menu = Menu(board)

      val firstMove =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P2 ($500) at GO, P3 ($500) at GO, P4 ($500) at GO, P1 ($420) at CS
          |
          |P1 ($420) at CS
          |Company Store - $80
          |
          |
          |P2 ($500) at GO
          |
          |
          |P3 ($500) at GO
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin
      val secondMove =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P3 ($500) at GO, P4 ($500) at GO, P1 ($460) at CS, P2 ($460) at CS
          |
          |P1 ($460) at CS
          |Company Store - $80
          |
          |
          |P2 ($460) at CS
          |
          |
          |P3 ($500) at GO
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin
      val thirdMove =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P4 ($500) at GO, P1 ($500) at CS, P2 ($460) at CS, P3 ($460) at CS
          |
          |P1 ($500) at CS
          |Company Store - $80
          |
          |
          |P2 ($460) at C
          |
          |
          |P3 ($460) at CS
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin
      val fourthMove =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P1 ($540) at CS, P2 ($460) at CS, P3 ($460) at CS, P4 ($460) at CS
          |
          |P1 ($540) at CS
          |Company Store - $80
          |
          |
          |P2 ($460) at CS
          |
          |
          |P3 ($460) at CS
          |
          |
          |P4 ($460) at CS
          |
          |""".stripMargin

      menu.doMove()
      menu.showGame shouldBe firstMove
      menu.doMove()
      menu.showGame shouldBe secondMove
      menu.doMove()
      menu.showGame shouldBe thirdMove
      menu.doMove()
      menu.showGame shouldBe fourthMove

    }

    it("can do a game") {
      val board = new Board(List("P1", "P2", "P3", "P4"))
      val menu = Menu(board)

      val firstTurn =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P1 ($540) at CS, P2 ($460) at CS, P3 ($460) at CS, P4 ($460) at CS
          |
          |P1 ($540) at CS
          |Company Store - $80
          |
          |
          |P2 ($460) at CS
          |
          |
          |P3 ($460) at CS
          |
          |
          |P4 ($460) at CS
          |
          |""".stripMargin

      val secondTurn =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P1 ($615) at TH, P2 ($385) at TH, P3 ($385) at TH, P4 ($385) at TH
          |
          |P1 ($615) at TH
          |Company Store - $80
          |Thayer Hall - $150
          |
          |
          |P2 ($385) at TH
          |
          |
          |P3 ($385) at TH
          |
          |
          |P4 ($385) at TH
          |
          |""".stripMargin

      val thirdTurn =
        """|+----+----+----+----+----+
          || GO | TB |  C | CS |  H |
          |+----+----+----+----+----+
          ||  A | P  Stores    | JH |
          |+----+ h          E +----+
          || HG | y          d | BH |
          |+----+ s          u +----+
          ||  T |  Restaurants | TH |
          |+----+----+----+----+----+
          || GH | TF | GH | MH | DCA|
          |+----+----+----+----+----+
          |Players: P1 ($675) at TF, P2 ($325) at TF, P3 ($325) at TF, P4 ($325) at TF
          |
          |P1 ($675) at TF
          |Company Store - $80
          |Thayer Hall - $150
          |The Firstie - $120
          |
          |
          |P2 ($325) at TF
          |
          |
          |P3 ($325) at TF
          |
          |
          |P4 ($325) at TF
          |
          |""".stripMargin
    }

    val FourthTurn =
      """|+----+----+----+----+----+
        || GO | TB |  C | CS |  H |
        |+----+----+----+----+----+
        ||  A | P  Stores    | JH |
        |+----+ h          E +----+
        || HG | y          d | BH |
        |+----+ s          u +----+
        ||  T |  Restaurants | TH |
        |+----+----+----+----+----+
        || GH | TF | GH | MH | DCA|
        |+----+----+----+----+----+
        |Players: P1 ($15) at A, P2 (-$5) at A, P3 (-$5) at A, P4 (-$5) at A
        |
        |P1 ($15) at A
        |Company Store - $80
        |Thayer Hall - $150
        |The Firstie - $120
        |Arvin - $660
        |
        |
        |P2 (-$5) at A
        |
        |
        |P3 (-$5) at A
        |
        |
        |P4 (-$5) at A
        |
        |""".stripMargin

    menu.doTurn()
    menu.showGame shouldBe firstTurn
    menu.checkForWinner() shouldBe None
    menu.doTurn()
    menu.showGame shouldBe secondTurn
    menu.checkForWinner() shouldBe None
    menu.doTurn()
    menu.showGame shouldBe thirdTurn
    menu.checkForWinner() shouldBe None
    menu.doTurn()
    menu.showGame shouldBe fourthTurn
    menu.checkForWinner() shouldBe Some("P1")

  }
}
