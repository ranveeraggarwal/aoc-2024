import kotlin.math.abs

fun main() {
    val input = readInput("day02")
    println(part21(input))
    println(part22(input))
}

fun List<Int>.isSafe(): Boolean {
    fun Int.sign() = if (this == 0) 0 else this/abs(this)

    var current = this[0]
    val step = (this[1] - this[0]).sign()

    for (i in 1..<size) {
        val diff = this[i] - current
        if (diff.sign() != step ) {
            return false
        }
        if (abs(diff) > 3 || abs(diff) < 1) {
            return false
        }
        current = this[i]
    }
    return true
}

fun part21(input: List<String>) = input.map { level -> level.split(" ").map { it.toInt() }.isSafe() }.count { it }

fun possibilities(input: List<Int>):  List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    for (i in input.indices) {
        val newList = input.toMutableList()
        newList.removeAt(i)
        result.add(newList)
    }
    return result
}

fun part22(input: List<String>) = input.map { level -> level.split(" ").map { it.toInt() } }.map {
    if (it.isSafe()) {
        return@map true
    } else {
        possibilities(it).forEach { possibility ->
            if (possibility.isSafe()) {
                return@map true
            }
        }
    }
    return@map false
}.count { it }