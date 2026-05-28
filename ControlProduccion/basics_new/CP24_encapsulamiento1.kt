open class LineaProduccion(nombre: String, capacidadInicial: Int) {

    val nombre: String = nombre

    private var capacidadRestante: Int = capacidadInicial

    internal val codigoLinea: String =
        "LP${(1000..9999).random()}"

    protected open fun calcularEficiencia(): Double = ((capacidadInicial.toDouble() - capacidadRestante) / capacidadInicial.toDouble()) * 100

    fun producir(unidades: Int) {
        require(unidades > 0) { "Las unidades deben ser positivas" }
        capacidadRestante -= unidades
        println("Producidas: $unidades | Capacidad restante: ${consultarCapacidad()}")
    }

    fun detener( unidades: Int): Boolean {
        require(unidades > 0) { "Las unidades deben ser positivas" }
        if (unidades > capacidadRestante) {
            println("Capacidad insuficiente")
            return false
        }
        capacidadRestante -= unidades
        println("Detenidas: $unidades | Capacidad restante: ${consultarCapacidad()}")
        return true
    }

    fun consultarCapacidad(): String = "$capacidadRestante unidades"
}

class LineaEficiente(nombre: String, capacidadInicial: Int)
    : LineaProduccion(nombre, capacidadInicial) {

    override fun calcularEficiencia(): Double {
        return super.calcularEficiencia() * 1.2
    }

    fun aplicarBono() {
        val eficiencia = calcularEficiencia()
        producir((eficiencia / 10).toInt())
    }
}

fun main() {
    val linea = LineaProduccion("Linea A", 1000)

    linea.producir(300)
    linea.detener(100)
    linea.detener(800)

    println(linea.nombre)
    println(linea.consultarCapacidad())

    println("---- Linea Eficiente ----")

    val eficiente = LineaEficiente("Linea B", 2000)
    eficiente.aplicarBono()
    println(eficiente.consultarCapacidad())
}
