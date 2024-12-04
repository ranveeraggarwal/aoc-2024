fun main() {
    val input = readInput("day04")
    println(part41(input))
    println(part42(input))
}


fun part41(input: List<String>) : Int {
    val padded = listOf("R".repeat(input[0].length + 2)).let { it + input.map {i ->  "R" + i + "R" } + it }
    var count = 0
    for (i in 1..padded.size-2) {
        for (j in 1..padded[i].length-2) {
            if (padded[i][j] != 'X') continue
            if (padded[i-1][j] == 'M' && padded[i-2][j] =='A' && padded[i-3][j] == 'S') count++
            if (padded[i+1][j] == 'M' && padded[i+2][j] =='A' && padded[i+3][j] == 'S') count++
            if (padded[i][j-1] == 'M' && padded[i][j-2] =='A' && padded[i][j-3] == 'S') count++
            if (padded[i][j+1] == 'M' && padded[i][j+2] =='A' && padded[i][j+3] == 'S') count++
            if (padded[i-1][j-1] == 'M' && padded[i-2][j-2] =='A' && padded[i-3][j-3] == 'S') count++
            if (padded[i+1][j-1] == 'M' && padded[i+2][j-2] =='A' && padded[i+3][j-3] == 'S') count++
            if (padded[i-1][j+1] == 'M' && padded[i-2][j+2] =='A' && padded[i-3][j+3] == 'S') count++
            if (padded[i+1][j+1] == 'M' && padded[i+2][j+2] =='A' && padded[i+3][j+3] == 'S') count++
        }
    }
    return count
}

fun part42(input: List<String>)  : Int {
    val padded = listOf("R".repeat(input[0].length + 2)).let { it + input.map {i ->  "R" + i + "R" } + it }
    var count = 0
    for (i in 1..padded.size-2) {
        for (j in 1..padded[i].length-2) {
            if (padded[i][j] != 'A') continue
            // 13
            // 24
            if (padded[i-1][j-1] == 'M' && padded[i+1][j-1] == 'M' && padded[i-1][j+1] == 'S' && padded[i+1][j+1] == 'S') count++
            if (padded[i-1][j-1] == 'M' && padded[i+1][j-1] == 'S' && padded[i-1][j+1] == 'M' && padded[i+1][j+1] == 'S') count++
            if (padded[i-1][j-1] == 'S' && padded[i+1][j-1] == 'S' && padded[i-1][j+1] == 'M' && padded[i+1][j+1] == 'M') count++
            if (padded[i-1][j-1] == 'S' && padded[i+1][j-1] == 'M' && padded[i-1][j+1] == 'S' && padded[i+1][j+1] == 'M') count++
        }
    }
    return count
}