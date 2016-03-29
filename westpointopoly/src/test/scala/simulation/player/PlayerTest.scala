package simulation.player

import java.awt.Color

import org.scalatest.{FunSpec, Matchers}
import simulation.board.Board
import simulation.board.space.{PropertyGroup, Property}

/**
  * Created by adam on 1/26/16.
  */
class PlayerTest extends FunSpec with Matchers {

  describe("Player holds information such as name, money, and properties") {
    implicit val board = Board()
    it("has a name and starting money") {
      val player = Player("a")

      player.name shouldBe "a"
      player.money shouldBe 500
    }

    it("can be represented as a string") {
      val board = Board()
      val player = Player("a", board=board)

      player.name shouldBe "a"
      player.money shouldBe 500
      player.toString shouldBe "a ($500) at GO"
    }

    it("has properties") {
      val player = Player("a")

      player.properties shouldBe Set.empty[Property]

      val propertyGroup = PropertyGroup("Restaurants", Color.red)
      val property = Property("Grant Hall", 100, propertyGroup)
      player.properties += property

      player.properties shouldBe Set(property)
    }

  }

}
