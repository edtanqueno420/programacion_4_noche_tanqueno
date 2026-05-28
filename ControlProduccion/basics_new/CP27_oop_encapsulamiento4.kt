class Caja(val ancho: Double, val alto: Double, val profundidad: Double) {
    val volumen:      Double get() = ancho * alto * profundidad
    val superficie:   Double get() = 2 * (ancho*alto + ancho*profundidad + alto*profundidad)

    constructor(lado: Double) : this(lado, lado, lado)
    constructor(ancho: Int, alto: Int, prof: Int) : this(ancho.toDouble(), alto.toDouble(), prof.toDouble())

    override fun toString() = "Caja(${ancho}x${alto}x${profundidad}) | volumen=${"%.2f".format(volumen)}"
}

fun main() {
    val c1 = Caja(5.0, 3.0, 4.0)
    val c2 = Caja(4.0)
    val c3 = Caja(6, 2, 3)

    println(c1)
    println(c2)
    println(c3)
}
