class ProductoBase(titular: String, saldoInicial: Double) {

    val titular: String = titular

    private var saldo: Double = saldoInicial

    internal val numeroProducto: String =
        "CP-${(100000..999999).random()}"

    private fun calcularCostoFabricacion(): Double = saldo * 0.02

    private fun formatear(valor: Double): String =
        "$${"%.2f".format(valor)}"

    fun agregarCosto(monto: Double) {
        require(monto > 0) { "El monto debe ser positivo" }
        saldo += monto
        println("Costo agregado: ${formatear(monto)} | Costo total: ${consultarCosto()}")
    }

    fun deducirCosto(monto: Double): Boolean {
        require(monto > 0) { "El monto debe ser positivo" }
        if (monto > saldo) {
            println("Costo excede el presupuesto")
            return false
        }
        saldo -= monto
        println("Deducido: ${formatear(monto)} | Costo restante: ${consultarCosto()}")
        return true
    }

    fun consultarCosto(): String = formatear(saldo)
}

class ToleranciaPieza(valorMm: Double) {

    var valorMm: Double = valorMm
        set(value) {
            require(value >= 0.0) { "La tolerancia no puede ser negativa" }
            field = value
        }

    val enMilimetros: Double
        get() = valorMm

    val enMicras: Double
        get() = valorMm * 1000.0

    val descripcion: String
        get() = when {
            valorMm < 0.01 -> "Tolerancia ultra precisa"
            valorMm < 0.1 -> "Tolerancia de alta precision"
            valorMm < 0.5 -> "Tolerancia estandar"
            valorMm < 1.0 -> "Tolerancia amplia"
            else -> "Tolerancia holgada"
        }
}

fun main() {
    val producto = ProductoBase("Eje Transmision", 1000.0)

    producto.agregarCosto(500.0)
    producto.deducirCosto(200.0)
    producto.deducirCosto(2000.0)

    println("Producto: ${producto.titular}")
    println("Costo final: ${producto.consultarCosto()}")

    println("---------------")

    val tol = ToleranciaPieza(0.15)
    println("${tol.valorMm} mm = ${tol.enMicras} micras")
    println("Descripcion: ${tol.descripcion}")

    tol.valorMm = 0.005
    println("${tol.valorMm} mm -> ${tol.descripcion}")
}
