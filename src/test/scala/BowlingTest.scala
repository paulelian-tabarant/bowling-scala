class BowlingTest extends munit.FunSuite {

  test("score is zero when no pin is knocked down") {
    val bowling = new Bowling()

    for (_ <- 1 to 20) {
      bowling.roll(0)
    }

    assertEquals(bowling.score(), 0)
  }
}

class Bowling() {
  def score(): Int = 0

  def roll(i: Int): Unit = {

  }
}