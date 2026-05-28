class LineaProduccion1(nombre: String, capacidadInicial: Int) {

    val nombre: String = nombre

    private var capacidadRestante: Int = capacidadInicial

    internal val codigoLinea: String =
        "LP${(1000..9999).random()}"

    private fun calcularEficiencia(): Double = ((capacidadInicial.toDouble() - capacidadRestante) / capacidadInicial.toDouble()) * 100

    private fun formatear(valor: Int): String =
        "$valor unidades"

    fun producir(unidades: Int) {
        require(unidades > 0) { "Las unidades deben ser positivas" }
        capacidadRestante -= unidades
        println("Producidas: ${formatear(unidades)} | Capacidad restante: ${consultarCapacidad()}")
    }

    fun detener(unidades: Int): Boolean {
        require(unidades > 0) { "Las unidades deben ser positivas" }
        if (unidades > capacidadRestante) {
            println("Capacidad insuficiente")
            return false
        }
        capacidadRestante -= unidades
        println("Detenidas: ${formatear(unidades)} | Capacidad restante: ${consultarCapacidad()}")
        return true
    }

    fun consultarCapacidad(): String = formatear(capacidadRestante)
}

class TemperaturaHorno(celsius: Double) {

    var celsius: Double = celsius
        set(value) {
            require(value >= -50) { "Temperatura bajo el minimo operativo" }
            field = value
        }

    val fahrenheit: Double
        get() = celsius * 9.0 / 5.0 + 32.0

    val descripcion: String
        get() = when {
            celsius < 100  -> "Horno frio"
            celsius < 250 -> "Temperatura media"
            celsius < 500 -> "Temperatura alta"
            celsius < 800 -> "Muy alta"
            else         -> "Critico"
        }
}

fun main() {
    val linea = LineaProduccion1("Linea A", 1000)

    linea.producir(300)
    linea.detener(100)
    linea.detener(800)

    println("Linea: ${linea.nombre}")
    println("Capacidad final: ${linea.consultarCapacidad()}")

    println("---------------")

    val horno = TemperaturaHorno(350.0)
    println("${horno.celsius}°C = ${horno.fahrenheit}°F")
    println("Descripcion: ${horno.descripcion}")

    horno.celsius = 150.0
    println("${horno.celsius}°C -> ${horno.descripcion}")
}
