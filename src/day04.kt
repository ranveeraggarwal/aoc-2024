fun main() {
    val input = readInput("day04")
    val padded = listOf("R".repeat(input[0].length + 2)).let { it + input.map {i ->  "R" + i + "R" } + it }
    println(part41(padded))
    println(part42(padded))
}


fun part41(input: List<String>) : Int {
    var count = 0
    for (i in 1..input.size-2) {
        for (j in 1..input[i].length-2) {
            if (input[i][j] != 'X') continue
            if (input[i-1][j] == 'M' && input[i-2][j] =='A' && input[i-3][j] == 'S') count++
            if (input[i+1][j] == 'M' && input[i+2][j] =='A' && input[i+3][j] == 'S') count++
            if (input[i][j-1] == 'M' && input[i][j-2] =='A' && input[i][j-3] == 'S') count++
            if (input[i][j+1] == 'M' && input[i][j+2] =='A' && input[i][j+3] == 'S') count++
            if (input[i-1][j-1] == 'M' && input[i-2][j-2] =='A' && input[i-3][j-3] == 'S') count++
            if (input[i+1][j-1] == 'M' && input[i+2][j-2] =='A' && input[i+3][j-3] == 'S') count++
            if (input[i-1][j+1] == 'M' && input[i-2][j+2] =='A' && input[i-3][j+3] == 'S') count++
            if (input[i+1][j+1] == 'M' && input[i+2][j+2] =='A' && input[i+3][j+3] == 'S') count++
        }
    }
    return count
}

fun part42(input: List<String>)  : Int {
    fun List<String>.isX(i: Int, j: Int, x: String) =
        (this[i-1][j-1] == x[0] && input[i+1][j-1] == x[1] && input[i-1][j+1] == x[2] && input[i+1][j+1] == x[3])
    var count = 0
    for (i in 1..input.size-2) {
        for (j in 1..input[i].length-2) {
            if (input[i][j] != 'A') continue
            if (input.isX(i, j, "MMSS")) count++
            if (input.isX(i, j, "MSMS")) count++
            if (input.isX(i, j, "SSMM")) count++
            if (input.isX(i, j, "SMSM")) count++
        }
    }
    return count
}