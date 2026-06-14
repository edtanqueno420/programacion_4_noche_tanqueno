class LoteProduccion(val cantidad: Int, val piezasOk: Int) {
    val tasaCalidad: Double get() = piezasOk.toDouble() / cantidad * 100.0
    val piezasRechazo: Int get() = cantidad - piezasOk

    constructor(cantidad: Int) : this(cantidad, cantidad)
    constructor(cantidad: String, piezasOk: String) : this(cantidad.toIntOrNull() ?: 0, piezasOk.toIntOrNull() ?: 0)

    override fun toString() = "Lote($cantidad unidades) | tasa calidad=${"%.1f".format(tasaCalidad)}%"
}

fun main() {
    val l1 = LoteProduccion(500, 485)
    val l2 = LoteProduccion(300)
    val l3 = LoteProduccion("250", "240")

    println(l1)
    println(l2)
    println(l3)
}
