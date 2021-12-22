import kotlin.math.min

val start1 = 10
val start2 = 1
val maxScore = 1000

class Dice100() {
    private var value = 0
    var rolls = 0
    fun roll(): Int {
        value++
        if (value == 101) value = 1
        return value
    }

}

fun main() {
    var score1 = 0
    var score2 = 0
    var position1 = start1
    var position2 = start2
    val die = Dice100()
    while (score1 < maxScore && score2 < maxScore) {
        var count = die.roll() + die.roll() + die.roll()
        die.rolls += 3
        position1 = getposition(position1, count)
        score1 += position1
        if (score1 >= maxScore) break
        count = die.roll() + die.roll() + die.roll()
        die.rolls += 3
        position2 = getposition(position2, count)
        score2 += position2
    }
    println(die.rolls)
    println(score1)
    println(score2)
    println(min(score1, score2) * die.rolls)
}

fun getposition(position: Int, count: Int): Int {
    var pos = position
    pos -= 1
    pos = (pos + count) % 10 +1
    return pos
}
