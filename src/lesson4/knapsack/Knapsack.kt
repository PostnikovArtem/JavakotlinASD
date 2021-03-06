package lesson4.knapsack

import java.util.*

data class Fill(val cost: Int, val items: List<Item>) {
    operator fun plus(fill: Fill) = Fill(cost + fill.cost, items + fill.items)

    constructor(cost: Int, vararg items: Item): this(cost, items.asList())

    constructor(item: Item): this(item.cost, item)
}

data class LoadCount(val load: Int, val count: Int)

data class Item(val cost: Int, val weight: Int)

fun fillKnapsackDynamic(load: Int, items: List<Item>,
                        storage: HashMap<LoadCount, Fill> = hashMapOf()): Fill {
    if (load <= 0 || items.isEmpty()) return Fill(0, emptyList())
    val itemsWithoutLast = items.subList(0, items.size - 1)
    val fillWithoutLast = fillKnapsackDynamic(load, itemsWithoutLast, storage)
    val last = items.last()
    val bestFill =
            if (last.weight > load) fillWithoutLast
            else {
                val fillWithLast = fillKnapsackDynamic(load - last.weight, itemsWithoutLast, storage) + Fill(last)
                if (fillWithLast.cost > fillWithoutLast.cost) fillWithLast
                else fillWithoutLast
            }
    storage[LoadCount(load, items.size)] = bestFill
    return bestFill
}

private fun fillKnapsackGreedySorted(load: Int, items: List<Item>): Fill {
    if (load <= 0 || items.isEmpty()) return Fill(0, emptyList())
    val itemsWithoutLast = items.subList(0, items.size - 1)
    val last = items.last()
    if (last.weight > load) return fillKnapsackGreedySorted(load, itemsWithoutLast)
    else return fillKnapsackGreedySorted(load - last.weight, itemsWithoutLast) + Fill(last)
}

fun fillKnapsackGreedy(load: Int, items: List<Item>): Fill {
    val sorted = items.sortedWith(Comparator<Item> {
        o1, o2 -> (o1.cost.toDouble() / o1.weight).compareTo(o2.cost.toDouble() / o2.weight)
    })
    return fillKnapsackGreedySorted(load, sorted)
}

fun main(args: Array<String>) {
    val items = listOf(Item(8, 10), Item(5, 12), Item(6, 8), Item(10, 15), Item(4, 2))
    println(fillKnapsackDynamic(30, items))
    println(fillKnapsackGreedy(30, items))
}