class BowlingTest extends munit.FunSuite {
  var bowling: Bowling = _
  val strike = 10

  override def beforeEach(context: BeforeEach): Unit = {
    bowling = new Bowling
  }

  test("outputs 0 when no pin is knocked down") {
    rollTimes(20, 0)

    assertEquals(bowling.score(), 0)
  }

  test("outputs 40 when 2 is done at each roll") {
    rollTimes(20, 2)

    assertEquals(bowling.score(), 40)
  }

  test("doubles first roll of next frame when a spare is made") {
    bowling.roll(2)
    bowling.roll(8)
    bowling.roll(3)
    rollTimes(17, 0)

    assertEquals(bowling.score(), 16)
  }

  test("does not consider two rolls that sum 10 on two adjacent frames as a spare") {
    bowling.roll(1)
    bowling.roll(8)
    bowling.roll(2)
    bowling.roll(3)
    rollTimes(16, 0)

    assertEquals(bowling.score(), 1 + 8 + 2 + 3)
  }

  test("doubles the next two rolls when a strike is made") {
    bowling.roll(strike)
    bowling.roll(7)
    bowling.roll(2)
    rollTimes(16, 0)

    assertEquals(bowling.score(), 10 + 2 * (7 + 2))
  }

  test("recognizes spares even if a strike has been made before") {
    bowling.roll(strike)
    bowling.roll(8)
    bowling.roll(2)
    bowling.roll(5)
    rollTimes(17, 0)

    assertEquals(bowling.score(), 10 + 2 * (8 + 2) + 2 * 5)
  }

  test("adds two bonus rolls on the last frame if a strike is made") {
    rollTimes(18, 0)
    bowling.roll(strike)
    bowling.roll(2)
    bowling.roll(7)

    assertEquals(bowling.score(), 19)
  }

  // acceptance test
  test("outputs 300 when perfect game") {
    rollTimes(12, strike)

    assertEquals(bowling.score(), 300)
  }

  private def rollTimes(times: Int, pins: Int): Unit = {
    for (_ <- 1 to times) {
      bowling.roll(pins)
    }
  }
}

