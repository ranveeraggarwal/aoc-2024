import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("X:\\Workspace\\AdventOfCode\\aoc24\\test\\", "$name.txt").readLines()