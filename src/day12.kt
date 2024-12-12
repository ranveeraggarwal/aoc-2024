fun main() {
    val input = readInput("day12")
    val padded = listOf(".".repeat(input[0].length + 2)).let { it + input.map { i -> ".$i." } + it }
    println(day121(padded))
    println(day122(padded))
}

fun day121(input: List<String>): Int {
    val fences = MutableList(input.size) { MutableList(input[0].length) { 0 } }

    for (i in 1..<input.size - 1) {
        for (j in 1..<input[0].length - 1) {
            if (input[i][j] != input[i - 1][j]) fences[i][j]++
            if (input[i][j] != input[i + 1][j]) fences[i][j]++
            if (input[i][j] != input[i][j - 1]) fences[i][j]++
            if (input[i][j] != input[i][j + 1]) fences[i][j]++
        }
    }

    lateinit var visited: MutableList<MutableList<Boolean>>

    fun iterate(i: Int, j: Int): Int {
        if (visited[i][j]) return 0
        visited[i][j] = true

        var area = fences[i][j]

        if (input[i - 1][j] == input[i][j]) area += iterate(i - 1, j)
        if (input[i + 1][j] == input[i][j]) area += iterate(i + 1, j)
        if (input[i][j - 1] == input[i][j]) area += iterate(i, j - 1)
        if (input[i][j + 1] == input[i][j]) area += iterate(i, j + 1)

        return area
    }

    var sum = 0
    for (i in 1..<input.size - 1) {
        for (j in 1..<input[0].length - 1) {
            visited = MutableList(input.size) { MutableList(input[0].length) { false } }
            sum += iterate(i, j)
        }
    }


    return sum
}

class Fence {
    var left = true
    var top = true
    var right = true
    var bottom = true

    fun perimeter() = listOf(left, top, right, bottom).count { it }
}

fun day122(input: List<String>): Int {
    val fences = MutableList(input.size) { MutableList(input[0].length) { Fence() } }

    // initial fencing
    for (i in 1..<input.size - 1) {
        for (j in 1..<input[0].length - 1) {
            if (input[i][j] == input[i][j + 1]) {
                fences[i][j].right = false
                fences[i][j+1].left = false
            }
            if (input[i][j] == input[i + 1][j]) {
                fences[i][j].bottom = false
                fences[i+1][j].top = false
            }
            if (input[i][j] == input[i][j - 1]) {
                fences[i][j].left = false
                fences[i][j-1].right = false
            }
            if (input[i][j] == input[i - 1][j]) {
                fences[i][j].top = false
                fences[i-1][j].bottom = false
            }

        }
    }

    // fence collapse
    for (i in 1..<input.size - 1) {
        for (j in 1..<input[0].length - 1) {
            if (input[i][j] == input[i][j + 1]) {
                if (fences[i][j+1].top) fences[i][j].top = false
                if (fences[i][j+1].bottom) fences[i][j].bottom = false
            }
            if (input[i][j] == input[i + 1][j]) {
                if (fences[i+1][j].left) fences[i][j].left = false
                if (fences[i+1][j].right) fences[i][j].right = false
            }
        }
    }

    lateinit var visited: MutableList<MutableList<Boolean>>

    fun iterate(i: Int, j: Int): Int {
        if (visited[i][j]) return 0
        visited[i][j] = true

        var area = fences[i][j].perimeter()

        if (input[i - 1][j] == input[i][j]) area += iterate(i - 1, j)
        if (input[i + 1][j] == input[i][j]) area += iterate(i + 1, j)
        if (input[i][j - 1] == input[i][j]) area += iterate(i, j - 1)
        if (input[i][j + 1] == input[i][j]) area += iterate(i, j + 1)

        return area
    }

    var sum = 0
    for (i in 1..<input.size - 1) {
        for (j in 1..<input[0].length - 1) {
            visited = MutableList(input.size) { MutableList(input[0].length) { false } }
            sum += iterate(i, j)
        }
    }


    return sum
}