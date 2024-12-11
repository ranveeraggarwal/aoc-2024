import java.math.BigInteger

fun main() {
    val input = readInput("day11")
    println(day111(input[0]))
    println(day112(input[0]))
}

fun findStones(stone: String, blink: Int, memory: MutableMap<Pair<Int, String>, Long>) : Long {
    // change to 26 for part 1 lol.
    if (blink == 76) return 1L
    if (!memory.containsKey(Pair(blink, stone))) {
        memory[Pair(blink, stone)] = stone.let {
            if (stone == "0") return@let findStones("1", blink+1, memory)
            if (stone.length % 2 == 0)
                return@let findStones(stone.substring(0, stone.length/2), blink+1, memory) + findStones(stone.substring(stone.length/2).trimStart { it == '0' }.ifEmpty { "0" }, blink+1, memory)
            else return@let findStones((BigInteger(stone).multiply(BigInteger("2024"))).toString(), blink+1, memory)
        }
    }
    return memory[Pair(blink, stone)]!!
}

fun day111(input: String): Long {
    val memory = mutableMapOf<Pair<Int, String>, Long>()
    var sum = 0L
    for (i in input.split(" ")) {
        sum += findStones(i, 1, memory)
    }

    return sum
}

fun day112(input: String): Long {
    val memory = mutableMapOf<Pair<Int, String>, Long>()
    var sum = 0L
    for (i in input.split(" ")) {
        sum += findStones(i, 1, memory)
    }

    return sum
}