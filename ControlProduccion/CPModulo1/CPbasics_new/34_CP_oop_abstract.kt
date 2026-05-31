abstract class PiezaBase(val nombre: String) {
    abstract val area: Double
    abstract val perimetro: Double
    abstract fun descripcion(): String

    fun comparar(otra: PiezaBase): String = when {
        area > otra.area -> "$nombre es mas grande que ${otra.nombre}"
        area < otra.area -> "$nombre es mas pequena que ${otra.nombre}"
        else -> "$nombre y ${otra.nombre} tienen la misma area"
    }

    override fun toString() = "${descripcion()} | Area: ${"%.2f".format(area)}"
}

class ChapaCircular(val radio: Double) : PiezaBase("Chapa Circular") {
    override val area: Double get() = Math.PI * radio * radio
    override val perimetro: Double get() = 2 * Math.PI * radio
    override fun descripcion() = "Chapa circular de radio $radio mm"
}

class ChapaRectangular(val ancho: Double, val alto: Double) : PiezaBase("Chapa Rectangular") {
    override val area: Double get() = ancho * alto
    override val perimetro: Double get() = 2 * (ancho + alto)
    override fun descripcion() = "Chapa rectangular de ${ancho}x${alto} mm"
}

class ChapaTriangular(val lado: Double) : PiezaBase("Chapa Triangular") {
    override val area: Double get() = (Math.sqrt(3.0) / 4) * lado * lado
    override val perimetro: Double get() = 3 * lado
    override fun descripcion() = "Chapa triangular de lado $lado mm"
}

fun main() {
    val chapas: List<PiezaBase> = listOf(
        ChapaCircular(50.0),
        ChapaRectangular(40.0, 60.0),
        ChapaTriangular(80.0)
    )

    chapas.forEach { println(it) }

    val mayor = chapas.maxByOrNull { it.area }
    println("\nChapa mas grande: ${mayor?.nombre}")

    println(chapas[0].comparar(chapas[1]))
}
