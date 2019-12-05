import kotlin.math.absoluteValue

fun main() {
  val input = Helpers.splitted("day3.txt")

  val coordinates = mutableSetOf<Pair<Int, Int>>()
  val intersections = mutableSetOf<Pair<Int, Int>>()

  // Per unique path all costs for the coordinates
  val pathCosts = mutableMapOf<String, Map<Pair<Int, Int>, Int>>()

  input.forEach { path ->
    var current = Pair(0, 0)
    val pathCoordinates = mutableSetOf<Pair<Int, Int>>()

    val costs = mutableMapOf<Pair<Int, Int>, Int>()
    pathCosts[path.joinToString()] = costs
    var costCounter = 0

    path.forEach { instruction ->
      val direction = instruction.first()
      val amount = instruction.substring(1).toInt()


      repeat(amount) {
        current = when (direction) {
          'U' -> Pair(current.first, current.second.inc())
          'D' -> Pair(current.first, current.second.dec())
          'R' -> Pair(current.first.inc(), current.second)
          else -> Pair(current.first.dec(), current.second)
        }

        pathCoordinates.add(current)

        // Only register the first cost for a coordinate
        costs.putIfAbsent(current, ++costCounter)

        // Check intersect with a previous path
        if (coordinates.contains(current)) {
          intersections.add(current)
        }
      }
    }

    coordinates.addAll(pathCoordinates)
  }

  val distance = intersections.map { (x, y) ->
    x.absoluteValue + y.absoluteValue
  }.min()

  println(distance)

  val intersectionCost = intersections.map { pair ->
    val a = pathCosts.values.first()
    val b = pathCosts.values.last()

    a[pair]!! + b[pair]!!
  }.min()

  println(intersectionCost)
}
