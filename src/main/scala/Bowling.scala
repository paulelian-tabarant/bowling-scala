import scala.collection.mutable.ArrayBuffer

class Bowling {
  private val TOTAL_PINS = 10
  private val LAST_FRAME = 10
  private val rolls: ArrayBuffer[Int] = ArrayBuffer()

  def roll(pins: Int): Unit = {
    rolls += pins
  }

  def score(): Int = {
    var result = 0
    var strikesCount = 0

    for (framesCount <- 1 to LAST_FRAME) {
      val rollsCount = (framesCount - 1) * 2 - strikesCount + 1

      if (framesCount == LAST_FRAME && isStrike(rollsCount)) {
        result += strikeScore(rollsCount)
      }

      else if (isSpare(rollsCount)) {
        result += rollAt(rollsCount + 2)
      }

      else if (isStrike(rollsCount)) {
        result += strikeScore(rollsCount)
        strikesCount += 1
      }

      if (!isStrike(rollsCount)) {
        result += rollAt(rollsCount) + rollAt(rollsCount + 1)
      }
    }

    result
  }

  private def strikeScore(index: Int): Int = {
    TOTAL_PINS + rollAt(index + 1) + rollAt(index + 2)
  }

  private def rollAt(index: Int) = {
    if (index - 1 <= rolls.size - 1) rolls(index - 1) else 0
  }

  private def isStrike(index: Int) = {
    rollAt(index) == TOTAL_PINS
  }

  private def isSpare(index: Int) = {
    rollAt(index) + rollAt(index + 1) == TOTAL_PINS
  }
}
