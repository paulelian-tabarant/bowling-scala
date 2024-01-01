class BowlingTest extends munit.FunSuite {
  var bowling: Bowling = _

  override def beforeEach(context: BeforeEach): Unit = {
    bowling = new Bowling
  }

  test("score is 0 when no pin is knocked down") {
    rollTimes(20, 0)

    assertEquals(bowling.score(), 0)
  }

  test("score is 40 when 2 is done at each roll") {
    rollTimes(20, 2)

    assertEquals(bowling.score(), 40)
  }

  test("score of next roll is doubled when a spare has been made") {
    bowling.roll(2)
    bowling.roll(8)
    bowling.roll(3)
    rollTimes(17, 0)

    assertEquals(bowling.score(), 16)
  }

  test("should not consider two rolls that sum 10 on two adjacent frames as a spare") {
    bowling.roll(1)
    bowling.roll(8)
    bowling.roll(2)
    bowling.roll(3)
    rollTimes(16, 0)

    assertEquals(bowling.score(), 1 + 8 + 2 + 3)
  }

  test("should double the next two rolls when a strike is made") {
    val strike = 10

    bowling.roll(strike)
    bowling.roll(7)
    bowling.roll(2)
    rollTimes(16, 0)

    assertEquals(bowling.score(), 10 + 2 * (7 + 2))
  }

  test("should recognize spares even if a strike has been made before") {
    val strike = 10

    bowling.roll(strike)
    bowling.roll(8)
    bowling.roll(2)
    bowling.roll(5)
    rollTimes(17, 0)

    assertEquals(bowling.score(), 10 + 2 * (8 + 2) + 2 * 5)
  }

  test("should add two bonus rolls on the last frame if a strike is made") {
    val strike = 10

    rollTimes(18, 0)
    bowling.roll(strike)
    bowling.roll(0)
    bowling.roll(7)

    assertEquals(bowling.score(), 17)
  }

  private def rollTimes(times: Int, pins: Int): Unit = {
    for (_ <- 1 to times) {
      bowling.roll(pins)
    }
  }
}

