package simulation.board

import scala.util.Random

/**
  * Created by x87039 on 1/31/2016.
  */
object Roll {
  def roll = Random.nextInt % 4
  def apply() = (roll, roll)
}
