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

    for (framesCount <- 1 to 10) {
      val index = (framesCount - 1) * 2 - strikesCount

      if (framesCount <= LAST_FRAME) {
        if (framesCount == LAST_FRAME && isStrike(index)) {
          result += strikeScore(index)
        }

        else if (isSpare(index)) {
          result += rollAt(index + 2)
        }

        else if (isStrike(index)) {
          result += strikeScore(index)
          strikesCount += 1
        }

        if (!isStrike(index)) {
          result += rollAt(index) + rollAt(index + 1)
        }
      }
    }

    result
  }

  private def strikeScore(index: Int): Int = {
    TOTAL_PINS + rollAt(index + 1) + rollAt(index + 2)
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
