import kotlin.math.abs

fun main() {
    val input = readInput("day01")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>) =
    input.map { it.split("   ").map(String::toInt) }.let { mainList ->
        mainList.map { it[0] }.sorted().zip(mainList.map { it[1] }.sorted()).sumOf { abs(it.first - it.second) }
    }

fun part2(input: List<String>) = input.map { it.split("   ").map(String::toInt) }.let { mainList ->
    mainList.map { it[0] }.sorted().sumOf { el1 -> mainList.map { it[1] }.sorted().count { el2 -> el1 == el2 } * el1 }
}