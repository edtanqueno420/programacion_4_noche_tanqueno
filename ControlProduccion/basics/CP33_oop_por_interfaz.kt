interface Pagable {
    fun procesar(monto: Double): Boolean
    val nombre: String
}

class TransferenciaBancaria(val cuenta: String) : Pagable {
    override val nombre = "Transferencia bancaria"
    override fun procesar(monto: Double): Boolean {
        println("Transfiriendo $${"%.2f".format(monto)} a $cuenta")
        return true
    }
}

class PagoEfectivo : Pagable {
    override val nombre = "Efectivo"
    override fun procesar(monto: Double): Boolean {
        println("Entregando $${"%.2f".format(monto)} en efectivo")
        return true
    }
}

class ChequeNomina : Pagable {
    override val nombre = "Cheque"
    override fun procesar(monto: Double): Boolean {
        println("Emitiendo cheque por $${"%.2f".format(monto)}")
        return true
    }
}

fun pagarNomina(monto: Double, metodoPago: Pagable) {
    println("Procesando pago con ${metodoPago.nombre}...")
    val exito = metodoPago.procesar(monto)
    println(if (exito) "Pago exitoso" else "Pago fallido")
}

fun main() {
    val metodos: List<Pagable> = listOf(
        TransferenciaBancaria("ES1234567890"),
        PagoEfectivo(),
        ChequeNomina()
    )

    metodos.forEach { pagarNomina(1500.0, it) }
}
