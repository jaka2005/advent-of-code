import kotlin.math.max

enum class Color {
    RED, GREEN, BLUE
}

class Day2(private val lines: List<String>, private val condition: Map<Color, Int>) {
    fun solveFirstPart() = lines.sumOf { line ->
        val (gameIdx, games) = line.split(": ")
        val idx = gameIdx.substring(5).toInt()

        val maxCubes = getMaxCubes(games)

        if (isPossibleGame(maxCubes, condition)) idx
        else 0
    }

    fun solveSecondPart() = lines.sumOf { line ->
        getMaxCubes(line.split(": ")[1]).values.reduce { acc, el -> acc * el }
    }

    private fun getMaxCubes(games: String): MutableMap<Color, Int> {
        val maxCubes = mutableMapOf(
            Color.RED to 0,
            Color.GREEN to 0,
            Color.BLUE to 0
        )

        games.split("; ").forEach { combination ->
            combination.split(", ").forEach {
                val (strNum, color) = it.split(" ")
                val key = Color.valueOf(color.uppercase())
                maxCubes[key] = max(maxCubes[key]!!, strNum.toInt())
            }
        }
        return maxCubes
    }
}

fun isPossibleGame(game: Map<Color, Int>, condition: Map<Color, Int>) = game[Color.RED]!! <= condition[Color.RED]!!
            && game[Color.GREEN]!! <= condition[Color.GREEN]!!
            && game[Color.BLUE]!! <= condition[Color.BLUE]!!
