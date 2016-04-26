package simulation.board

import scala.util.Random

/** A dice that returns rolls for the board.
  *
  * @param rolls an iterator of future dice rolls
  */
class Dice(rolls: Iterator[(Int, Int)]) {

  /** Returns the next dice roll as tuple. */
  def roll = rolls.next

}

/** Factory for new dice. */
object Dice {

  /** Returns a new dice with a max value of the mod. */
  def apply(mod: Int = 4) = {
    def rolls: Stream[(Int, Int)] = (Random.nextInt(mod) + 1, Random.nextInt(mod) + 1) #:: rolls
    new Dice(rolls.toIterator)
  }

  /** Returns a new dice from a custom iterator. */
  def apply(rolls: Iterator[(Int, Int)]) = new Dice(rolls)

}