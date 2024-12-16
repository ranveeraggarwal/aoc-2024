import Direction.*
import java.util.LinkedList
import java.util.Queue


fun main() {
    val input = readInput("day16")
    println(day161(input))
    println(day162(input))
}

fun findPoint(input: List<String>, letter: Char): Point {
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (input[i][j] == letter) return Point(i, j)
        }
    }
    throw IllegalArgumentException("This is pointless")
}

fun getNextPointWithDirection(initial: Point, newDirection: Direction) =
    PointWithDirection(
        when (newDirection) {
            NORTH -> Point(initial.x - 1, initial.y)
            EAST -> Point(initial.x, initial.y + 1)
            WEST -> Point(initial.x, initial.y - 1)
            SOUTH -> Point(initial.x + 1, initial.y)
        }, newDirection
    )

fun getScores(input: List<String>, startPoint: PointWithDirection): Map<PointWithDirection, Int> {
    infix fun List<String>.at(point: Point): Char = this[point.x][point.y]

    val minScores = mutableMapOf<PointWithDirection, Int>()

    val bfs: Queue<PointWithDirection> = LinkedList<PointWithDirection>().also {
        it.add(startPoint)
    }
    minScores[startPoint] = 0

    while (bfs.isNotEmpty()) {
        with(bfs.remove()) {
            listOf(
                direction.left() to 1001,
                direction.right() to 1001,
                direction to 1
            ).forEach { (newDirection, cost) ->
                val next = getNextPointWithDirection(
                    location,
                    newDirection
                )
                if (input at next.location != '#') {
                    val newCost = minScores[this]!! + cost
                    if (!minScores.containsKey(next) || newCost < minScores[next]!!) {
                        minScores[next] = newCost
                        bfs.add(next)
                    }
                }
            }

        }
    }

    return minScores
}

fun day161(input: List<String>): Int {
    val startPoint = findPoint(input, 'S')
    val endPoint = findPoint(input, 'E')

    val minScores = getScores(input, PointWithDirection(startPoint, EAST))

    return Direction.entries.minOf { minScores[PointWithDirection(endPoint, it)] ?: Int.MAX_VALUE }
}

fun day162(input: List<String>): Int {
    val startPoint = findPoint(input, 'S')
    val endPoint = findPoint(input, 'E')

    val minScoresForward = getScores(input, PointWithDirection(startPoint, EAST))
    val minScoresBackward = getScores(input, PointWithDirection(endPoint, WEST))

    val minScore = Direction.entries.minOf { minScoresBackward[PointWithDirection(startPoint, it)] ?: Int.MAX_VALUE }
    println(minScore)

    var count = 0
    for (i in input.indices) {
        for (j in input[i].indices) {
            for (dir in Direction.entries) {
                val forward = PointWithDirection(Point(i, j), dir)
                val backward = PointWithDirection(Point(i, j), dir.back())
                if (!minScoresForward.containsKey(forward) || !minScoresBackward.containsKey(backward)) continue
                println("${minScoresForward[forward]} ${minScoresBackward[backward]}")
                if (minScoresForward[forward]!! + minScoresBackward[backward]!! == minScore) count++
            }
        }
    }
    return count
}