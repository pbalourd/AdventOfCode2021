fun main() {
    // Input
    // target area: x=117..164, y=-140..-89
    var yMax = 0
    for (uy in -1000..1000) {
        var y = 0
        var u = uy
        var t = 0
        var i = false
        for (step in 1..1000) {
            y += u
            if (y > t) {
                t = y
            }
            if (y in -140..-89) {
                i = true
            }
            u--
        }
        if (i) yMax = t
    }
    println(yMax)

    var count = 0
    for (ux in 1..164) {
        for (uy in -1000..1000) {
            var y = 0
            var u2 = uy
            var x = 0
            var u1 = ux
            var i = false
            for (step in 1..1000) {
                x += u1
                y += u2
                if (x in 117..164 && y in -140..-89) i = true
                if (u1 > 0) u1--
                u2--
            }
            if (i) count++
        }
    }

    println(count)

}


