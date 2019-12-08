object IntCode {

  fun run(program: MutableList<Int>, inputVal: Int = 0) = run(program, listOf(inputVal))

  fun run(program: MutableList<Int>, inputVals: List<Int>): Int {
    var pos = 0
    var inputCounter = 0

    while (true) {
      val opcode = program[pos] % 100

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
          program[program[pos + 1]] = inputVals[inputCounter++]
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
        99 -> return 0
      }
    }
  }
}
