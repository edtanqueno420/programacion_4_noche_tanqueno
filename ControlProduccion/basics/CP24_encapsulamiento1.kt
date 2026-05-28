open class Maquina(tipo: String, capacidadInicial: Double) {
    val tipo: String = tipo
    private var capacidad: Double = capacidadInicial
    internal val codigoMaquina: String = "M-${(1000..9999).random()}"
    protected open fun calcularEficiencia(): Double = capacidad * 0.85

    fun producir(monto: Double) {
        require(monto > 0) { "La cantidad debe ser positiva" }
        capacidad += monto
        println("Producido: ${"%.2f".format(monto)} unidades | Total: ${verCapacidad()}")
    }

    fun retirar(monto: Double): Boolean {
        require(monto > 0) { "La cantidad debe ser positiva" }
        if (monto > capacidad) {
            println("Capacidad insuficiente")
            return false
        }
        capacidad -= monto
        println("Retirado: ${"%.2f".format(monto)} | Restante: ${verCapacidad()}")
        return true
    }

    fun verCapacidad(): String = "${"%.2f".format(capacidad)} unidades"
}

class MaquinaPremium(tipo: String, capacidadInicial: Double) : Maquina(tipo, capacidadInicial) {
    override fun calcularEficiencia(): Double {
        return super.calcularEficiencia() * 1.5
    }

    fun aplicarBonificacion() {
        val bono = calcularEficiencia()
        producir(bono)
    }
}

fun main() {
    val maquina = Maquina("Torno CNC", 1000.0)
    maquina.producir(500.0)
    maquina.retirar(200.0)
    maquina.retirar(2000.0)
    println(maquina.tipo)
    println(maquina.verCapacidad())

    println("---- Maquina Premium ----")
    val premium = MaquinaPremium("Centro Mecanizado", 2000.0)
    premium.aplicarBonificacion()
    println(premium.verCapacidad())
}
