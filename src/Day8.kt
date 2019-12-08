fun main() {
  val input = Helpers.lines("day8.txt").first().toCharArray().map { c -> c.toString().toInt() }

  val layers = mutableListOf<MutableList<Int>>()
  val perLayer = 25 * 6

  input.forEachIndexed { index, pixel ->
    val layer = index / perLayer

    if (layers.lastIndex < layer) {
      layers.add(layer, mutableListOf())
    }

    layers[layer].add(pixel)
  }

  val minLayer = layers.minBy { layer -> layer.count { it == 0 } }!!
  println(minLayer.count { it == 1 } * minLayer.count { it == 2 })

  val finalLayer = (0 until perLayer).map { index ->
    val nonTransLayer = layers.find { layer -> layer[index] != 2 }!!
    nonTransLayer[index]
  }

  (0 until perLayer).forEach { pixel ->
    print(
      when (finalLayer[pixel]) {
        0 -> "\u2588"
        else -> " "
      }
    )

    if (pixel % 25 == 0) {
      println()
    }
  }
}
