package simulation.board

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adam on 1/26/16.
  */
class PropertyGroupTest extends FunSpec with Matchers {

  describe("Property group serves to group Properties and keeps track of the properties that belong to it") {

    it("has a name and color") {
      val propertyGroup = PropertyGroup("Restaurants", (255, 0, 0))

      propertyGroup.name shouldBe "Restaurants"
      propertyGroup.color shouldBe (255, 0, 0)
    }

    it("holds what properties belong to it") {
      val restaurants = PropertyGroup("Restaurants", (255, 0, 0))

      restaurants.properties shouldBe Set.empty[Property]

      val grantHall = Property("Grant Hall", 100, restaurants)
      val messHall = Property("Mess Hall", 50, restaurants)

      restaurants.properties shouldBe Set(grantHall, messHall)
    }

  }

}
