fun main() {

  fun factorial(max: Int): Int = (2..max).fold(1, { x, y -> x * y })

  fun permutations(list: List<Int>): List<List<Int>> {
    fun permutation(count: Int, input: MutableList<Int>, output: MutableList<Int>): List<Int> {
      if (input.isEmpty()) {
        return output
      }

      val fac = factorial(input.count() - 1)
      output.add(input.removeAt(count / fac))
      return permutation(count % fac, input, output)
    }

    return (0 until factorial(list.count())).map { i -> permutation(i, list.toMutableList(), mutableListOf()) }
  }

  val program = Helpers.splitted("day7.txt").flatten().intList()

  println(permutations((0..4).toList()).map { sequence ->
    val programCopy = program.toMutableList()
    sequence.fold(0, { prevOutput, input -> IntCode.run(programCopy, listOf(input, prevOutput)) })
  }.max())


}
