import inputdata.TEST_D2_CONDITION
import inputdata.TEST_D3

fun main() {
    val d = Day3(readFileText("day3.txt")!!.lines())
//    val d = Day3(TEST_D3.lines())

    println(d.solveFirstPart())
    println(d.solveSecondPart())
}

fun readFileText(path: String) =
    object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.use { it.readText() }

