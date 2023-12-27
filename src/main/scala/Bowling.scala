import scala.collection.mutable.ListBuffer

class Bowling {
  private val TOTAL_PINS = 10
  private val rolls: ListBuffer[Int] = ListBuffer()

  def roll(pins: Int): Unit = {
    rolls += pins
  }

  def score(): Int = {
    var result = 0

    rolls.indices.sliding(2, 2).foreach { pair =>
      val index = pair.head

      if (isSpare(index)) {
        result += rolls(index + 2)
      }

      result += rolls(index) + rolls(index + 1)
    }

    result
  }

  private def isSpare(index: Int) = {
    index < rolls.size - 1 && rolls(index) + rolls(index + 1) == TOTAL_PINS
  }
}
