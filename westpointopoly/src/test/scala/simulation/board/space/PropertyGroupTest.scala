package simulation.board.space

import java.awt.Color

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adam on 1/26/16.
  */
class PropertyGroupTest extends FunSpec with Matchers {

  describe("Property group serves to group Properties and keeps track of the properties that belong to it") {

    it("has a name and color") {
      val propertyGroup = PropertyGroup("Restaurants", Color.red)

      propertyGroup.name shouldBe "Restaurants"
      propertyGroup.color shouldBe Color.red
    }

    it("holds what properties belong to it") {
      val restaurants = PropertyGroup("Restaurants", Color.red)

      restaurants.properties shouldBe Set.empty[Property]

      val grantHall = Property("Grant Hall", 100, restaurants)
      val messHall = Property("Mess Hall", 50, restaurants)

      restaurants.properties shouldBe Set(grantHall, messHall)
    }

  }

}
