fun main() {

    val start = 10000000000000L
    val end =   99999999999999L

    val s = listOf(1,  1,  1,  1,  1,  26,  1, 26, 26,  1, 26, 26, 26, 26)
    val k = listOf(13, 11, 12, 10, 14, -1, 14, -16,-8, 12,-16,-13, -6, -6)
    val l = listOf(6,  11, 5,  6,   8, 14,  9,   4, 7, 13, 11, 11,  6,  1)

    var noZeros = "[1-9]+".toRegex()
    for (a in end downTo start) {
        if (!noZeros.matches(a.toString())) continue
        var div =   10000000000000L
        var w = 0
        var x = 0
        var y = 0
        var z = 0
        for (i in 1..14) {
            val d = (a/div - (a/(div*10))* 10).toInt()
//            println(d)
//

            w = d
//            x = 0
//            x += z
//            x %= 26
            x = z % 26

            z /= s[i -1]
            x += k[i -1]
            x = if (x == w) 1 else 0
            x = if (x == 0) 1 else 0
//            y = 0
//            y += 25
//            y *= x
//            y += 1
            y= 25 * x + 1

            z *= y
            y = 0
            y += w


            y += l[i -1]
            y *= x
            z += y


//
            div /= 10
        }
        if (z == 0) println(a)
//        println(a)
    }

}


