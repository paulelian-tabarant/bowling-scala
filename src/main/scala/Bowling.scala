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
    var strikes = 0

    for (frame <- 1 to LAST_FRAME) {
      val roll = countRolls(frame, strikes)

      if (isSpare(roll)) {
        result += pinsDownAt(roll + 2)
      }

      else if (isStrike(roll)) {
        result += pinsDownAt(roll + 1) + pinsDownAt(roll + 2)
        strikes += 1
      }

      result += frameScore(roll)
    }

    result
  }

  private def countRolls(frame: Int, strikes: Int): Int = {
    (frame - 1) * 2 - strikes + 1
  }

  private def frameScore(roll: Int): Int = {
    if (isStrike(roll)) {
      pinsDownAt(roll)
    } else {
      pinsDownAt(roll) + pinsDownAt(roll + 1)
    }
  }

  private def pinsDownAt(roll: Int) = {
    rolls(roll - 1)
  }

  private def isStrike(roll: Int) = {
    pinsDownAt(roll) == TOTAL_PINS
  }

  private def isSpare(roll: Int) = {
    pinsDownAt(roll) + pinsDownAt(roll + 1) == TOTAL_PINS
  }
}
