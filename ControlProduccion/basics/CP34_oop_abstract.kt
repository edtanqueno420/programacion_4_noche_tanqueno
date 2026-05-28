abstract class PiezaMecanica(val nombre: String) {
    abstract val area: Double
    abstract val perimetro: Double
    abstract fun descripcion(): String

    fun comparar(otra: PiezaMecanica): String = when {
        area > otra.area -> "$nombre es mas grande que ${otra.nombre}"
        area < otra.area -> "$nombre es mas pequena que ${otra.nombre}"
        else -> "$nombre y ${otra.nombre} tienen la misma area"
    }

    override fun toString() = "${descripcion()} | Area: ${"%.2f".format(area)}"
}

class Arandela(val radio: Double) : PiezaMecanica("Arandela") {
    override val area: Double get() = Math.PI * radio * radio
    override val perimetro: Double get() = 2 * Math.PI * radio
    override fun descripcion() = "Arandela de radio $radio"
}

class PlacaRectangular(val ancho: Double, val alto: Double) : PiezaMecanica("Placa") {
    override val area: Double get() = ancho * alto
    override val perimetro: Double get() = 2 * (ancho + alto)
    override fun descripcion() = "Placa de ${ancho}x${alto}"
}

class Triangular(val lado: Double) : PiezaMecanica("Triangular") {
    override val area: Double get() = (Math.sqrt(3.0) / 4) * lado * lado
    override val perimetro: Double get() = 3 * lado
    override fun descripcion() = "Triangular de lado $lado"
}

fun main() {
    val piezas: List<PiezaMecanica> = listOf(
        Arandela(5.0),
        PlacaRectangular(4.0, 6.0),
        Triangular(8.0)
    )

    piezas.forEach { println(it) }

    val mayor = piezas.maxByOrNull { it.area }
    println("\nPieza mas grande: ${mayor?.nombre}")

    println(piezas[0].comparar(piezas[1]))
}
