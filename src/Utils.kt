import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("X:\\Workspace\\AdventOfCode\\aoc24\\test\\", "$name.txt").readLines()

data class Point(val x: Int, val y: Int)

enum class Direction{
    NORTH, EAST, SOUTH, WEST;

    fun left() = when(this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }

    fun right() = when(this) {
        NORTH -> EAST
        WEST -> NORTH
        SOUTH -> WEST
        EAST -> SOUTH
    }

    fun back() = when(this) {
        NORTH -> SOUTH
        WEST -> EAST
        EAST -> WEST
        SOUTH -> NORTH
    }
}

data class PointWithDirection(val location: Point, val direction: Direction)