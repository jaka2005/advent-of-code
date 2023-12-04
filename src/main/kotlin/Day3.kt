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
                isNeighbourOf(it, coord, numStr.length)
            }?.let { numStr.toInt() } ?: 0
        }
    }


    fun solveSecondPart(): Int {
        val (nums, details) = parseScheme()

        return details.filterValues { it == '*' }.keys.sumOf { it ->
            var produce = 1
            var findedNums = 0
            nums.keys.forEach { coord ->
                if (isNeighbourOf(it, coord, nums[coord]!!.length)) {
                    findedNums += 1
                    produce *= nums[coord]!!.toInt()
                }
            }
            if (findedNums >= 2) produce else 0
        }
    }

    private fun isNeighbourOf(it: Coordinate, coord: Coordinate, width: Int) =
        (it.x in (coord.x - 1)..(coord.x + width)
                && it.y in (coord.y - 1)..(coord.y + 1))

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
