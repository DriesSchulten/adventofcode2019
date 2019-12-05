fun isSixDigits(str: String) = str.length == 6

fun hasTwoSameAdjecentDigits(str: String) = str.zipWithNext { a, b -> a == b }.any { it }

fun hasOnlyIncreasingDigits(str: String) = str.zipWithNext { a, b -> b >= a }.all { it }

// Part 2
fun containsNoOrAtLeastOnePair(str: String): Boolean {
  var subList = mutableListOf<Char>()

  val lists = mutableListOf<List<Char>>()
  str.forEach { curr ->
    if (subList.isNotEmpty() && subList.last() == curr) {
      subList.add(curr)
    } else {
      if (subList.isNotEmpty()) {
        lists.add(subList)
      }

      subList = mutableListOf(curr)
    }
  }

  if (subList.isNotEmpty()) {
    lists.add(subList)
  }

  val counts = lists.map { it.count() }.distinct()
  return counts.contains(2) || counts.max() == 1
}

fun main() {
  val range = 246515..739105

  val valid = range.filter {
    val digits = it.toString()

    val valid = isSixDigits(digits) && hasTwoSameAdjecentDigits(digits) && hasOnlyIncreasingDigits(digits)
    valid && containsNoOrAtLeastOnePair(digits) // Part 2
  }.count()

  println(valid)
}
