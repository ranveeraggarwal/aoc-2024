fun main() {
    val input = readInput("day13")
    println(day131(input.filter { it.isNotEmpty() }.chunked(3)))
    println(day132(input.filter { it.isNotEmpty() }.chunked(3)))
}

data class Solveable(val a1: Long, val a2: Long, val b1: Long, val b2: Long, val c1: Long, val c2: Long)

fun day131(input: List<List<String>>) = input.sumOf {
    with(Solveable(
        it[0].split(",")[0].split("+")[1].toLong(),
        it[0].split(",")[1].split("+")[1].toLong(),
        it[1].split(",")[0].split("+")[1].toLong(),
        it[1].split(",")[1].split("+")[1].toLong(),
        it[2].split(",")[0].split("=")[1].toLong(),
        it[2].split(",")[1].split("=")[1].toLong(),
    )) {
        if ((c1 * a2 - c2 * a1) % (b1 * a2 - b2 * a1) != 0L) return@with 0L
        val b = (c1 * a2 - c2 * a1) / (b1 * a2 - b2 * a1)
        if ((c1 - b1 * b) % a1 != 0L) return@with 0L
        val a = (c1 - b1 * b) / a1

        b + 3 * a
    }
}

fun day132(input: List<List<String>>) = input.sumOf {
    with(Solveable(
        it[0].split(",")[0].split("+")[1].toLong(),
        it[0].split(",")[1].split("+")[1].toLong(),
        it[1].split(",")[0].split("+")[1].toLong(),
        it[1].split(",")[1].split("+")[1].toLong(),
        it[2].split(",")[0].split("=")[1].toLong() + 10000000000000,
        it[2].split(",")[1].split("=")[1].toLong() + 10000000000000,
    )) {
        if ((c1 * a2 - c2 * a1) % (b1 * a2 - b2 * a1) != 0L) return@with 0L
        val b = (c1 * a2 - c2 * a1) / (b1 * a2 - b2 * a1)
        if ((c1 - b1 * b) % a1 != 0L) return@with 0L
        val a = (c1 - b1 * b) / a1

        b + 3 * a
    }
}