class Day1(private val lines: List<String>) {
    private val digitWords = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun solveFirstPart() = lines.sumOf(::processLine)

    fun solveSecondPart() = lines.sumOf { line ->
        processLine(
            line.mapIndexedNotNull { index, c ->
                if (c.isDigit()) c
                else (3..5).map {
                    line.substring(index, (index + it).coerceAtMost(line.length))
                }.firstNotNullOfOrNull {
                    digitWords[it]
                }
            }.joinToString()
        )
    }


    private fun processLine(line: String) =
        "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
}
