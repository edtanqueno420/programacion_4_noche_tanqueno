class OrdenProduccion(val codigo: String, val pieza: String) {
    val codigoNormalizado: String
    val piezaNormalizada: String

    init {
        require(codigo.isNotBlank()) { "El codigo de orden no puede estar vacio" }
        require(pieza.isNotBlank()) { "La pieza no puede estar vacia" }

        codigoNormalizado = codigo.trim().uppercase()
        piezaNormalizada = pieza.trim().lowercase()
    }
}

fun main() {
    val op = OrdenProduccion("  OP-001  ", "  EJE DE TRANSMISION  ")
    println(op.codigoNormalizado)
    println(op.piezaNormalizada)
}
