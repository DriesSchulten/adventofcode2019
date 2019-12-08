import java.util.*

data class Planet(var indirect: Int = 0, val plannets: MutableSet<String> = mutableSetOf())

fun main() {
  val input = Helpers.lines("day6.txt")

  val orbits = mutableMapOf<String, Planet>()
  input.forEach { str ->
    val (a, b) = str.split(")")

    orbits.putIfAbsent(a, Planet())
    orbits[a]?.plannets?.add(b)
  }

  // BFS
  val queue: Queue<String> = LinkedList()
  queue.offer("COM")

  var indirect = 0
  while (queue.isNotEmpty()) {
    val planetName = queue.poll()

    val planet = orbits[planetName]!!
    indirect += planet.indirect

    planet.plannets.forEach { otherPlanet ->
      if (!orbits.containsKey(otherPlanet)) {
        indirect += planet.indirect + 1
      } else {
        orbits[otherPlanet]!!.indirect = planet.indirect + 1
        queue.offer(otherPlanet)
      }
    }
  }

  println(indirect)

  val paths = mutableMapOf<String, String>()
  input.forEach { str ->
    val (a, b) = str.split(")")

    paths[b] = a
  }

  fun getPath(target: String): List<String> {
    val path = mutableListOf(target)

    var curr = target
    while (curr != "COM") {
      curr = paths[curr]!!
      path.add(curr)
    }

    return path.reversed()
  }

  val san = getPath("SAN")
  val you = getPath("YOU")
  val max = kotlin.math.min(san.count(), you.count())

  val difference = (0 until max).find { i -> san[i] != you[i] }!!
  println(san.subList(difference, san.lastIndex).count() + you.subList(difference, you.lastIndex).count())
}
