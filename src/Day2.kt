fun part1(output: MutableList<Int>) {
  output[1] = 12
  output[2] = 2

  IntCode.run(output)
  println(output[0])
}

fun part2(output: MutableList<Int>) {
  for (noun in 0..99) {
    for (verb in 0..99) {
      val memory = output.toMutableList()

      memory[1] = noun
      memory[2] = verb

      IntCode.run(memory)

      if (memory[0] == 19690720) {
        println(100 * noun + verb)
      }
    }
  }
}

fun main() {
  val input = Helpers.splitted("day2.txt").flatten().intList().toMutableList()

  part1(input.toMutableList())
  part2(input.toMutableList())
}
