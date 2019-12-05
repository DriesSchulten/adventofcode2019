fun part1(output: MutableList<Int>) {
  output[1] = 12
  output[2] = 2

  println(run(output))
}

fun part2(output: MutableList<Int>) {
  for (noun in 0..99) {
    for (verb in 0..99) {
      val memory = output.toMutableList()

      memory[1] = noun
      memory[2] = verb

      val result = run(memory)

      if (result == 19690720) {
        println(100 * noun + verb)
      }
    }
  }
}

fun run(output: MutableList<Int>): Int {
  var pos = 0
  var opcode = output[pos]

  while (opcode != 99) {
    val target = output[pos + 3]
    val noun = output[output[pos + 1]]
    val verb = output[output[pos + 2]]

    when (opcode) {
      1 -> output[target] = noun + verb
      2 -> output[target] = noun * verb
    }

    pos += 4
    opcode = output[pos]
  }

  return output[0]
}

fun main() {
  val input = Helpers.splitted("day2.txt").flatten().intList().toMutableList()

  part1(input.toMutableList())
  part2(input.toMutableList())
}
