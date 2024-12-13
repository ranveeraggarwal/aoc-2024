fun main() {
    val input = readInput("day13")
    println(day131(input.filter { it.isNotEmpty() }.chunked(3)))
    println(day132(input.filter { it.isNotEmpty() }.chunked(3)))
}

data class Solveable(val a1: Int, val a2: Int, val b1: Int, val b2: Int, val c1: Long, val c2: Long)

fun day131(input: List<List<String>>): Long {
    var sum = 0L
    for (eqs in input) {
        with(Solveable(
            eqs[0].split(",")[0].split("+")[1].toInt(),
            eqs[0].split(",")[1].split("+")[1].toInt(),
            eqs[1].split(",")[0].split("+")[1].toInt(),
            eqs[1].split(",")[1].split("+")[1].toInt(),
            eqs[2].split(",")[0].split("=")[1].toLong(),
            eqs[2].split(",")[1].split("=")[1].toLong(),
        )) {
            if ((c1*a2 - c2*a1)%(b1*a2 - b2*a1) != 0L) return@with
            val b = (c1*a2 - c2*a1)/(b1*a2 - b2*a1)
            if ((c1 - b1*b)%a1 != 0L) return@with
            val a = (c1 - b1*b)/a1

            sum += b + 3*a
        }
    }
    return sum
}

fun day132(input: List<List<String>>): Long {
    var sum = 0L
    for (eqs in input) {
        with(Solveable(
            eqs[0].split(",")[0].split("+")[1].toInt(),
            eqs[0].split(",")[1].split("+")[1].toInt(),
            eqs[1].split(",")[0].split("+")[1].toInt(),
            eqs[1].split(",")[1].split("+")[1].toInt(),
            eqs[2].split(",")[0].split("=")[1].toLong() + 10000000000000,
            eqs[2].split(",")[1].split("=")[1].toLong() + 10000000000000,
        )) {
            if ((c1*a2 - c2*a1)%(b1*a2 - b2*a1) != 0L) return@with
            val b = (c1*a2 - c2*a1)/(b1*a2 - b2*a1)
            if ((c1 - b1*b)%a1 != 0L) return@with
            val a = (c1 - b1*b)/a1

            sum += b + 3*a
        }
    }
    return sum
}