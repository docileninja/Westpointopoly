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

  }
}
