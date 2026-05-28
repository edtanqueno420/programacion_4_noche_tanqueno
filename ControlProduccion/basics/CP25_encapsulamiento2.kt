class MaquinaProduccion(tipo: String, capacidadInicial: Double) {
    val tipo: String = tipo
    private var capacidad: Double = capacidadInicial
    internal val codigo: String = "M-${(1000..9999).random()}"
    private fun calcularEficiencia(): Double = capacidad * 0.02
    private fun formatear(valor: Double): String = "${"%.2f".format(valor)} unidades"

    fun producir(monto: Double) {
        require(monto > 0) { "La cantidad debe ser positiva" }
        capacidad += monto
        println("Producido: ${formatear(monto)} | Total: ${verCapacidad()}")
    }

    fun retirar(monto: Double): Boolean {
        require(monto > 0) { "La cantidad debe ser positiva" }
        if (monto > capacidad) {
            println("Capacidad insuficiente")
            return false
        }
        capacidad -= monto
        println("Retirado: ${formatear(monto)} | Restante: ${verCapacidad()}")
        return true
    }

    fun verCapacidad(): String = formatear(capacidad)
}

class TemperaturaHorno(celsius: Double) {
    var celsius: Double = celsius
        set(value) {
            require(value >= -50) { "Temperatura bajo el minimo operativo" }
            field = value
        }

    val fahrenheit: Double get() = celsius * 9.0 / 5.0 + 32.0
    val kelvin: Double get() = celsius + 273.15

    val estado: String get() = when {
        celsius < 100  -> "Frio"
        celsius < 250  -> "Calentando"
        celsius < 400  -> "Operativo"
        celsius < 600  -> "Caliente"
        else -> "Peligro - Sobrecalentamiento"
    }
}

fun main() {
    val maq = MaquinaProduccion("Prensa Hidraulica", 5000.0)
    maq.producir(1500.0)
    maq.retirar(800.0)
    maq.retirar(6000.0)
    println("Tipo: ${maq.tipo}")
    println("Capacidad final: ${maq.verCapacidad()}")

    println("---------------")

    val horno = TemperaturaHorno(350.0)
    println("${horno.celsius}°C = ${horno.fahrenheit}°F = ${horno.kelvin}K")
    println("Estado: ${horno.estado}")

    horno.celsius = 150.0
    println("${horno.celsius}°C -> ${horno.estado}")
}
