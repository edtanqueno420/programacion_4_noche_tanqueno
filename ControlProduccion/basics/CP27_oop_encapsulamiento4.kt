class LaminaMetal(val ancho: Double, val alto: Double) {
    val area: Double get() = ancho * alto
    val perimetro: Double get() = 2 * (ancho + alto)

    constructor(lado: Double) : this(lado, lado)
    constructor(ancho: Int, alto: Int) : this(ancho.toDouble(), alto.toDouble())

    override fun toString() = "Lamina(${ancho}x${alto}) | area=${area}"
}

fun main() {
    val l1 = LaminaMetal(5.0, 3.0)
    val l2 = LaminaMetal(4.0)
    val l3 = LaminaMetal(6, 2)

    println(l1)
    println(l2)
    println(l3)
}
