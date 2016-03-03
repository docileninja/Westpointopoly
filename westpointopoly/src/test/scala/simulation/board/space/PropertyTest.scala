package simulation.board.space

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adam on 1/26/16.
  */
class PropertyTest extends FunSpec with Matchers {

  describe("Properties are owned by players and have a purchase price and rent. They also belong to a PropertyGroup.") {

    it("the purchase price must be twice the rent") {
      val propertyGroup = PropertyGroup("Restaurants", (0,0,0))
      val property = Property("Grant Hall", 100, propertyGroup)

      property.name shouldBe "Grant Hall"
      property.cost shouldBe 100
      property.rent shouldBe 50
    }

    it("can be represented as a string") {
      val propertyGroup = PropertyGroup("Restaurants", (0,0,0))
      val property = Property("Grant Hall", 100, propertyGroup)

      property.name shouldBe "Grant Hall"
      property.cost shouldBe 100
      property.toString shouldBe "Grant Hall - $100"
    }

  }

}
