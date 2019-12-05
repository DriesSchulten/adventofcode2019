import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

object Helpers {

  fun lines(name: String): List<String> {
    val file = Paths.get(Helpers::class.java.getResource("/$name").file);
    return Files.lines(file).toList()
  }

  fun splitted(name: String): List<List<String>> {
    return lines(name).map { it.split(',') }
  }
}

fun List<String>.intList(): List<Int> = this.map { it.toInt() }
