fun main() {
  val program = Helpers.splitted("day5.txt").flatten().intList().toMutableList()

  println(IntCode.run(program.toMutableList(), 1))
  println(IntCode.run(program.toMutableList(), 5))
}
