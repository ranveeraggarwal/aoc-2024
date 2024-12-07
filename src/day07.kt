fun main() {
    val input = readInput("day07")
    println(part71(input))
    println(part72(input))
}


fun part71(input: List<String>) = input.sumOf {
    val test = it.split(": ")[0].toLong()
    val values = it.split(": ")[1].split(" ").map { num -> num.toLong() }

    val memory = List(values.size) { mutableSetOf<Long>() }
    memory[0].add(values[0])

    for (i in 1..<values.size) {
        memory[i].addAll(memory[i-1].map { previous -> previous + values[i] })
        memory[i].addAll(memory[i-1].map { previous -> previous * values[i] })
    }

    return@sumOf if (memory[values.size - 1].contains(test)) test else 0
}

fun part72(input: List<String>) = input.sumOf {
    val test = it.split(": ")[0].toLong()
    val values = it.split(": ")[1].split(" ").map { num -> num.toLong() }

    val memory = List(values.size) { mutableSetOf<Long>() }
    memory[0].add(values[0])

    for (i in 1..<values.size) {
        memory[i].addAll(memory[i-1].map { previous -> previous + values[i] })
        memory[i].addAll(memory[i-1].map { previous -> previous * values[i] })
        memory[i].addAll(memory[i-1].map { previous -> (previous.toString() + values[i]).toLong() })
    }

    return@sumOf if (memory[values.size - 1].contains(test)) test else 0
}