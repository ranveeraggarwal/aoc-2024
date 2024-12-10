fun main() {
    val input = readInput("day10")
    println(day101(input))
    println(day102(input))
}

fun day101(input: List<String>) : Int {
    fun iterateOverGrid(input: List<String>, i:Int, j:Int, scores: MutableList<MutableList<Int>>) {
        if (scores[i][j] > 0) return
        scores[i][j]++
        val next = input[i][j].digitToInt() + 1
        if (next == 10) return
        if (i > 0 && input[i-1][j].digitToInt() == next) {
            iterateOverGrid(input, i-1, j, scores)
        }
        if (j > 0 && input[i][j-1].digitToInt() == next) {
            iterateOverGrid(input, i, j-1, scores)
        }
        if (i < input.size - 1 && input[i+1][j].digitToInt() == next) {
            iterateOverGrid(input, i+1, j, scores)
        }
        if (j < input[0].length - 1 && input[i][j+1].digitToInt() == next) {
            iterateOverGrid(input, i, j+1, scores)
        }
    }

    fun getTotalScore(scores: MutableList<MutableList<Int>>) : Int {
        var sum9 = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '9') {
                    sum9 += scores[i][j]
                }
            }
        }
        return sum9
    }

    var sum = 0
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (input[i][j] == '0') {
                val scores = MutableList(input.size) {MutableList(input[0].length) { 0 } }
                iterateOverGrid(input, i, j, scores)
                sum += getTotalScore(scores)
            }
        }
    }
    return sum
}

fun day102(input: List<String>) : Int {
    fun iterateOverGrid(input: List<String>, i:Int, j:Int, scores: MutableList<MutableList<Int>>) {
        scores[i][j]++
        val next = input[i][j].digitToInt() + 1
        if (next == 10) return
        if (i > 0 && input[i-1][j].digitToInt() == next) {
            iterateOverGrid(input, i-1, j, scores)
        }
        if (j > 0 && input[i][j-1].digitToInt() == next) {
            iterateOverGrid(input, i, j-1, scores)
        }
        if (i < input.size - 1 && input[i+1][j].digitToInt() == next) {
            iterateOverGrid(input, i+1, j, scores)
        }
        if (j < input[0].length - 1 && input[i][j+1].digitToInt() == next) {
            iterateOverGrid(input, i, j+1, scores)
        }
    }

    fun getTotalScore(scores: MutableList<MutableList<Int>>) : Int {
        var sum9 = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '9') {
                    sum9 += scores[i][j]
                }
            }
        }
        return sum9
    }

    var sum = 0
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (input[i][j] == '0') {
                val scores = MutableList(input.size) {MutableList(input[0].length) { 0 } }
                iterateOverGrid(input, i, j, scores)
                sum += getTotalScore(scores)
            }
        }
    }
    return sum
}