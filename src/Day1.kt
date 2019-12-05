import kotlin.math.max

fun calcFuel(weight: Int): Int = (weight / 3) - 2

fun main() {
  val input = Helpers.lines("day1.txt").intList()

  println(input.map { calcFuel(it) }.sum())

  println(input.map { num ->
    var fuel = max(0, calcFuel(num))
    var total = fuel

    while (fuel > 0) {
      fuel = calcFuel(fuel)

      if (fuel > 0) {
        total += fuel
      }
    }

    total
  }.sum())
}
