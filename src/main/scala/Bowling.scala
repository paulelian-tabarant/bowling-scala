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

      if (isSpare(rollsCount)) {
        result += rollAt(rollsCount + 2)
      }

      else if (isStrike(rollsCount)) {
        result += rollAt(rollsCount + 1) + rollAt(rollsCount + 2)
        strikesCount += 1
      }

      result += frameScore(rollsCount)
    }

    result
  }

  private def frameScore(rollsCount: Int): Int = {
    if (isStrike(rollsCount))
      TOTAL_PINS
    else
      rollAt(rollsCount) + rollAt(rollsCount + 1)
  }

  private def rollAt(count: Int) = {
    if (count <= rolls.size) rolls(count - 1) else 0
  }

  private def isStrike(count: Int) = {
    rollAt(count) == TOTAL_PINS
  }

  private def isSpare(count: Int) = {
    rollAt(count) + rollAt(count + 1) == TOTAL_PINS
  }
}
