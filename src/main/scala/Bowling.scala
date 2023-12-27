import scala.collection.mutable.ListBuffer

class Bowling {
  private val TOTAL_PINS = 10
  private val rolls: ListBuffer[Int] = ListBuffer()

  def roll(pins: Int): Unit = {
    rolls += pins
  }

  def score(): Int = {
    var result = 0
    var strikesCount = 0

    rolls.indices.sliding(2, 2).foreach { pair =>
      val index = pair.head - strikesCount

      if (isSpare(index)) {
        result += rollAt(index + 2)
      }

      if (isStrike(index)) {
        result += TOTAL_PINS
        result += rollAt(index + 1) + rollAt(index + 2)
        strikesCount += 1
      }

      else {
        result += rollAt(index) + rollAt(index + 1)
      }
    }

    result
  }

  private def rollAt(index: Int) = {
    if (index <= rolls.size - 1) rolls(index) else 0
  }

  private def isStrike(index: Int) = {
    rollAt(index) == TOTAL_PINS
  }

  private def isSpare(index: Int) = {
    rollAt(index) + rollAt(index + 1) == TOTAL_PINS
  }
}
