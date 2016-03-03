package simulation.board

import scala.util.Random

/**
  * Created by x87039 on 1/31/2016.
  */
class Dice(rolls: Iterator[(Int, Int)]) {
  def roll = rolls.next
}

object Dice {
  def apply(mod: Int = 4) = {
    def rolls: Stream[(Int, Int)] = (Random.nextInt(mod) + 1, Random.nextInt(mod) + 1) #:: rolls
    new Dice(rolls.toIterator)
  }
  def apply(rolls: Iterator[(Int, Int)]) = new Dice(rolls)
}