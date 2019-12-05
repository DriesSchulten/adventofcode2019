fun run(program: MutableList<Int>, inputVal: Int): Int {
  var pos = 0

  while (true) {
    var opcode = program[pos] % 100

    val mode1 = (program[pos] / 100) % 10
    val mode2 = (program[pos] / 1000) % 100

    val noun = if (mode1 == 0) program[program[pos + 1]] else program[pos + 1]
    val verb = try {
      if (mode2 == 0) program[program[pos + 2]] else program[pos + 2]
    } catch (_: IndexOutOfBoundsException) {
      0
    }

    when (opcode) {
      1 -> {
        program[program[pos + 3]] = noun + verb
        pos += 4
      }
      2 -> {
        program[program[pos + 3]] = noun * verb
        pos += 4
      }
      3 -> {
        program[program[pos + 1]] = inputVal
        pos += 2
      }
      4 -> {
        if (noun != 0) return noun
        pos += 2
      }
      5 -> {
        pos = if (noun != 0) verb else pos + 3
      }
      6 -> {
        pos = if (noun == 0) verb else pos + 3
      }
      7 -> {
        program[program[pos + 3]] = if (noun < verb) 1 else 0
        pos += 4
      }
      8 -> {
        program[program[pos + 3]] = if (noun == verb) 1 else 0
        pos += 4
      }
    }
  }

  0
}

fun main() {
  val program = Helpers.splitted("day5.txt").flatten().intList().toMutableList()

  println(run(program.toMutableList(), 1))
  println(run(program.toMutableList(), 5))
}
