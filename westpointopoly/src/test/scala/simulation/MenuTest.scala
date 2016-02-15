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
          |Players: P3 ($500) at GO, P4 ($500) at GO, P1 ($420) at CS, P2 ($400) at C
          |
          |P1 ($420) at CS
          |Company Store - $80
          |
          |
          |P2 ($400) at C
          |C-store - $100
          |
          |
          |P3 ($500) at GO
          |
          |
          |P4 ($500) at GO
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
          |Players: P4 ($500) at GO, P1 ($420) at CS, P2 ($400) at C, P3 ($460) at CS
          |
          |P1 ($460) at CS
          |Company Store - $80
          |
          |
          |P2 ($400) at C
          |C-store - $100
          |
          |
          |P3 ($460) at CS
          |
          |
          |P4 ($500) at GO
          |
          |""".stripMargin
      val fourthTurn =
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
          |Players: P1 ($420) at CS, P2 ($400) at C, P3 ($460) at CS, P4 ($500) at H
          |
          |P1 ($460) at CS
          |Company Store - $80
          |
          |
          |P2 ($400) at C
          |C-store - $100
          |
          |
          |P3 ($460) at CS
          |
          |
          |P4 ($500) at H
          |
          |""".stripMargin

      menu.doTurn()
      menu.showGame shouldBe firstTurn
      menu.doTurn()
      menu.showGame shouldBe secondTurn
      menu.doTurn()
      menu.showGame shouldBe thirdTurn
      menu.doTurn()
      menu.showGame shouldBe fourthTurn

    }

    it("can do a game") {

    }

  }
}
