import kotlin.math.abs

fun main() {
    val input = readInput("day03")
    println(part31(input))
    println(part32(input))
}

fun findAllMulsAndProductSum(input: String) =
    "mul\\([0-9]+,[0-9]+\\)".toRegex().findAll(input).sumOf {
        it.value.substring(4, it.value.length - 1).split(",").map { x -> x.toInt() }.let { nums ->
            nums[0] * nums[1]
        }
    }

fun part31(input: List<String>) = findAllMulsAndProductSum(input.joinToString())

fun part32(input: List<String>) =
    (input.joinToString().split("don't\\(\\)".toRegex()).map { it.split("do\\(\\)".toRegex()).drop(1) }
        .flatten() + input.joinToString().split("don't\\(\\)".toRegex())[0]).sumOf { findAllMulsAndProductSum(it) }