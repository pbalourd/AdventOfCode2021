import kotlin.math.max
import kotlin.math.min

const val start1 = 10
const val start2 = 1
const val maxScore1 = 1000
const val maxScore2 = 21

class Dice100() {
    private var value = 0
    var rolls = 0
    fun roll(): Int {
        return ++value % 100
    }
}

data class State(val score1: Int, val score2: Int, val pos1: Int, val pos: Int, val turn: Int)

fun main() {
    var score1 = 0L
    var score2 = 0L
    var position1 = start1
    var position2 = start2
    val die = Dice100()
    while (score1 < maxScore1 && score2 < maxScore1) {
        var count = die.roll() + die.roll() + die.roll()
        die.rolls += 3
        position1 = getPosition(position1, count)
        score1 += position1
        if (score1 >= maxScore1) break
        count = die.roll() + die.roll() + die.roll()
        die.rolls += 3
        position2 = getPosition(position2, count)
        score2 += position2
    }
    println(min(score1, score2) * die.rolls)
    // Solution 920079

    val diracDiceResultsFrequencies3Times = listOf(3 to 1, 4 to 3, 5 to 6, 6 to 7, 7 to 6, 8 to 3, 9 to 1)
    val cache = mutableMapOf<State, Pair<Long, Long>>()

    fun calculateWins(state: State): Pair<Long, Long> {
        val bb = cache[state]
        if (bb != null) return bb

        val  (s1, s2, p1, p2, turn) = state

        return if (s1 >= maxScore2) {
            Pair(1, 0)
        } else if (s2 >= maxScore2) {
            Pair(0, 1)
        } else {
            if (turn == 0) {
                var totalWinsPair = Pair(0L,0L)
                for (p in diracDiceResultsFrequencies3Times) {
                    val pos = getPosition(p1, p.first)
                    val winsPair = calculateWins(State(s1 + pos, s2, pos, p2, 1))
                    totalWinsPair = Pair(totalWinsPair.first + p.second * winsPair.first, totalWinsPair.second + p.second * winsPair.second)
                }
                cache[state] = totalWinsPair
                totalWinsPair
            }
            else {
                var totalWinsPair = Pair(0L,0L)
                for (p in diracDiceResultsFrequencies3Times) {
                    val pos = getPosition(p2, p.first)
                    val winsPair = calculateWins(State(s1, s2 + pos, p1, pos, 0))
                    totalWinsPair = Pair(totalWinsPair.first + p.second * winsPair.first, totalWinsPair.second + p.second * winsPair.second)
                }
                cache[state] = totalWinsPair
                totalWinsPair
            }
        }
    }

    // Naive implementation
    val wins = calculateWins(State(0, 0, start1, start2, 0))
    println(max(wins.first, wins.second))

    var p1Wins = 0L
    var p2Wins = 0L
    val queue = ArrayDeque<State>()
    // Uncomment the following line in order to execute
//    queue.add(State(0, 0, start1, start2, 0))

    while (queue.isNotEmpty()) {
        val a = queue.removeFirst()
        val  (s1, s2, p1, p2, turn) = a
        if (s1 >= maxScore2) {
            p1Wins++
            continue
        }
        else if (s2 >= maxScore2) {
            p2Wins++
            continue
        }
        if (turn == 0) {
            for (i in 1..3) {
                for (j in 1..3) {
                    for (k in 1..3) {
                        val pos = getPosition(p1, i + j + k)
                        queue.addFirst(State(s1 + pos, s2, pos, p2, 1))
                    }
                }
            }
        } else {
            for (i in 1..3) {
                for (j in 1..3) {
                    for (k in 1..3) {
                        val pos = getPosition(p2, i + j + k)
                        queue.addFirst(State(s1, s2 + pos, p1, pos, 0))
                    }
                }
            }
        }
    }
    println(p1Wins)
    println(p2Wins)
}

fun getPosition(position: Int, count: Int): Int {
    return (position - 1 + count) % 10 +1
}

