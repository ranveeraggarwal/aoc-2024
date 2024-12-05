// Absolute monstrosity, hope no one sees this.

fun main() {
    val input = readInput("day05")
    println(part51(input))
    println(part52(input))
}

fun buildPriorityMap(rules: List<String>): Map<Int, Set<Int>> {
    val map: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    for (rule in rules) {
        val pair = rule.split("|").map { it.toInt() }
        map.getOrPut(pair[0], {mutableSetOf()}).add(pair[1])
    }
    return map
}

fun part51(input: List<String>) : Int {
    val priorityMap = buildPriorityMap(input.subList(0, input.indexOf("")))
    var result = 0
    for (updates in input.subList(input.indexOf("") + 1, input.size).map { update -> update.split(",").map { it.toInt() } }) {
        val pageSet = mutableSetOf<Int>()
        var wasCorrectlyOrdered = true
        for (page in updates) {
            if (priorityMap[page]?.intersect(pageSet)?.isNotEmpty() == true) {
                wasCorrectlyOrdered = false
                break
            }
            pageSet.add(page)
        }
        if (wasCorrectlyOrdered) {
            result += updates[updates.size/2]
        }
    }
    return result
}

fun part52(input: List<String>)  : Int {
    val priorityMap = buildPriorityMap(input.subList(0, input.indexOf("")))
    var result = 0
    for (updates in input.subList(input.indexOf("") + 1, input.size).map { update -> update.split(",").map { it.toInt() } }) {
        val updatesClone = updates.toMutableList()
        val pageSet = mutableSetOf<Int>()
        var wasCorrectlyOrdered = true
        for (i in updates.indices) {
            val page = updatesClone[i]
            val intersect = priorityMap[page]?.intersect(pageSet)?: mutableSetOf()
            if (intersect.isNotEmpty()) {
                wasCorrectlyOrdered = false
                for (j in 0..i) {
                    if (intersect.contains(updatesClone[j])) {
                        updatesClone.removeAt(i)
                        updatesClone.add(j, page)
                        break
                    }
                }
            }
            pageSet.add(page)
        }
        if (!wasCorrectlyOrdered) {
            result += updatesClone[updatesClone.size/2]
        }
    }
    return result
}