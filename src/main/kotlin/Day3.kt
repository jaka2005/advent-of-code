enum class SymbolType {
    DIGIT, DETAIL, DOT;

    companion object {
        fun getBySymbol(char: Char): SymbolType =
            if (char.isDigit()) DIGIT else if (char == '.') DOT else DETAIL
    }
}

data class Coordinate(val x: Int, val y: Int)

class Day3(private val scheme: List<String>) {
    fun solveFirstPart(): Int {
        val (nums, details) = parseScheme()

        return nums.entries.sumOf { (coord, numStr) ->
            details.keys.find {
                ((coord.x - 1) <= it.x && it.x <= (coord.x + numStr.length)
                    && (coord.y - 1) <= it.y && it.y <= (coord.y + 1))
            }?.let { numStr.toInt() } ?: 0
        }
    }

    fun solveSecondPart(): Int {
        val (nums, details) = parseScheme()

        return details.filterValues { it == '*' }.keys.sumOf { it ->
            var produce = 1
            var findedNums: Int = 0
            nums.keys.forEach { coord ->
                if ((coord.x - 1) <= it.x && it.x <= (coord.x + nums[coord]!!.length)
                    && (coord.y - 1) <= it.y && it.y <= (coord.y + 1)) {
                    findedNums += 1
                    produce *= nums[coord]!!.toInt()
                }
            }
            if (findedNums >= 2) produce else 0
        }
    }

    private fun parseScheme(): Pair<MutableMap<Coordinate, String>, MutableMap<Coordinate, Char>> {
        val numStr = StringBuilder()
        var startNumCoord: Coordinate? = null // x and y
        val nums = mutableMapOf<Coordinate, String>() // coordinate the start of the num
        val details = mutableMapOf<Coordinate, Char>()

        scheme.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                when (val type = SymbolType.getBySymbol(c)) {
                    SymbolType.DIGIT -> {
                        numStr.append(c)
                        if (startNumCoord == null)
                            startNumCoord = Coordinate(x, y)
                    }

                    SymbolType.DETAIL,
                    SymbolType.DOT -> {
                        if (numStr.isNotEmpty() && startNumCoord != null) {
                            nums[startNumCoord!!] = numStr.toString()
                            numStr.clear()
                            startNumCoord = null
                        }
                        if (type == SymbolType.DETAIL) {
                            details[Coordinate(x, y)] = c
                        }
                    }
                }
            }
        }
        return Pair(nums, details)
    }


}
