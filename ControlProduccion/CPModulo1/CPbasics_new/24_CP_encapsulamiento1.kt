open class Producto(titular: String, saldoInicial: Double) {

    val titular: String = titular

    private var saldo: Double = saldoInicial

    internal val numeroProducto: String =
        "CP-${(100000..999999).random()}"

    protected open fun calcularCostoFabricacion(): Double = saldo * 0.02

    fun agregarCosto(monto: Double) {
        require(monto > 0) { "El monto debe ser positivo" }
        saldo += monto
        println("Costo agregado: $${"%.2f".format(monto)} | Costo total: ${consultarCosto()}")
    }

    fun deducirCosto(monto: Double): Boolean {
        require(monto > 0) { "El monto debe ser positivo" }
        if (monto > saldo) {
            println("Costo excede el presupuesto")
            return false
        }
        saldo -= monto
        println("Deducido: $${"%.2f".format(monto)} | Costo restante: ${consultarCosto()}")
        return true
    }

    fun consultarCosto(): String = "$${"%.2f".format(saldo)}"
}

class ProductoEspecial(titular: String, saldoInicial: Double)
    : Producto(titular, saldoInicial) {

    override fun calcularCostoFabricacion(): Double {
        return super.calcularCostoFabricacion() * 1.5
    }

    fun aplicarRecargo() {
        val recargo = calcularCostoFabricacion()
        agregarCosto(recargo)
    }
}

fun main() {
    val producto = Producto("Pieza Eje Industrial", 1000.0)

    producto.agregarCosto(500.0)
    producto.deducirCosto(200.0)
    producto.deducirCosto(2000.0)

    println(producto.titular)
    println(producto.consultarCosto())

    println("---- Producto Especial ----")

    val especial = ProductoEspecial("Engranaje Helicoidal", 2000.0)
    especial.aplicarRecargo()
    println(especial.consultarCosto())
}
