import scala.collection.mutable.ListBuffer

class Bowling {
  private val TOTAL_PINS = 10
  private val rolls: ListBuffer[Int] = ListBuffer()

  def score(): Int = {
    var result = 0

    for (index <- rolls.indices) {
      if (isSpare(index)) {
        result += rolls(index + 2)
      }
      result += rolls(index)
    }

    result
  }

  private def isSpare(index: Int) = {
    index < rolls.size - 1 && rolls(index) + rolls(index + 1) == TOTAL_PINS
  }

  def roll(pins: Int): Unit = {
    this.rolls += pins
  }
}
