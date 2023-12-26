class Bowling {
  private var total = 0

  def score(): Int = {
    total
  }

  def roll(pins: Int): Unit = {
    this.total += pins
  }
}
