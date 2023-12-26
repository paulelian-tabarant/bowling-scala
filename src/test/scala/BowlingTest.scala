import org.junit.Before

class BowlingTest extends munit.FunSuite {
  var bowling: Bowling = _

  override def beforeEach(context: BeforeEach): Unit = {
    bowling = new Bowling
  }

  test("score is 0 when no pin is knocked down") {
    rollMany(20, 0)

    assertEquals(bowling.score(), 0)
  }

  test("score is 40 when 2 is done at each roll") {
    rollMany(20, 2)

    assertEquals(bowling.score(), 40)
  }

  private def rollMany(times: Int, pins: Int): Unit = {
    for (_ <- 1 to times) {
      bowling.roll(pins)
    }
  }

}

