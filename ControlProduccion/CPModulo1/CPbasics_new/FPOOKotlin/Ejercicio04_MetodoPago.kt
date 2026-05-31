interface MetodoPago {
    fun procesarPago(monto: Double)
}

class PagoEfectivo : MetodoPago {
    override fun procesarPago(monto: Double) {
        println("Procesando pago en EFECTIVO por $monto")
        println("Monto recibido: $monto")
        println("Cambio: 0.00")
        println("Pago en efectivo completado.")
    }
}

class PagoTarjeta : MetodoPago {
    override fun procesarPago(monto: Double) {
        val recargo = monto * 0.05
        val total = monto + recargo
        println("Procesando pago con TARJETA por $monto")
        println("Recargo del 5%: $recargo")
        println("Total a cobrar: $total")
        println("Pago con tarjeta aprobado.")
    }
}

class PagoTransferencia : MetodoPago {
    override fun procesarPago(monto: Double) {
        println("Procesando pago por TRANSFERENCIA por $monto")
        println("Verificando datos de cuenta...")
        println("Transferencia realizada exitosamente.")
    }
}

fun main() {
    val metodosPago: List<MetodoPago> = listOf(
        PagoEfectivo(),
        PagoTarjeta(),
        PagoTransferencia()
    )

    val montos = listOf(150.0, 320.50, 1000.0)

    for (i in metodosPago.indices) {
        println("=== Metodo de pago ${i + 1} ===")
        metodosPago[i].procesarPago(montos[i])
        println()
    }

    println("=== Recorriendo todos los metodos con el mismo monto ===")
    for (metodo in metodosPago) {
        metodo.procesarPago(250.0)
        println()
    }
}
