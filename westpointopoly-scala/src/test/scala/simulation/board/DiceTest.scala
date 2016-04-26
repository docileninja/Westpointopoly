package simulation.board

import org.scalatest.{Matchers, FunSpec}

/**
  * Created by x87039 on 3/3/2016.
  */
class DiceTest extends FunSpec with Matchers {
  describe("A dice generates rolls for the board") {
    it("should be able to generate rolls from a given iterator") {
      val rolls = List.tabulate(4) {i: Int => (i, i)}.toIterator
      val dice = Dice(rolls)

      dice.roll shouldBe (0, 0)
      dice.roll shouldBe (1, 1)
      dice.roll shouldBe (2, 2)
      dice.roll shouldBe (3, 3)
    }
  }
}
