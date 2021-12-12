import java.io.File

// 2020 Day 3
fun main() {
    val inputFile = File("src/main/kotlin/test03.txt")

    val lineList = inputFile.readText().split("\r\n\r\n")

    println(lineList.size)

    val fields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var count = 0
    for (passport in lineList) {
        val passportData = passport.split(":", " ", "\n")
        if (passportData.containsAll(fields)) count++
    }
    println(count)
}


