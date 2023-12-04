import inputdata.TEST_D2_CONDITION

fun main() {
    val d = Day2(readFileText("day2.txt")!!.lines().filter { it.isNotEmpty() }, TEST_D2_CONDITION)

    println(d.solveFirstPart())
    println(d.solveSecondPart())
}

fun readFileText(path: String) =
    object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.use { it.readText() }

