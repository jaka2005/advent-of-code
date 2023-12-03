fun main() {
    val d1 = Day1(readFileText("day1.txt")!!.lines().filter { it.isNotEmpty() })

    println(d1.solveSecondPart())
}

fun readFileText(path: String) =
    object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.use { it.readText() }

