interface Serializable {
    val id: String
    fun serializar(): String
    val version: Int get() = 1
}

interface Validable {
    val errores: List<String>
    val esValido: Boolean get() = errores.isEmpty()

    fun validar(): Boolean
    fun imprimirErrores() {
        if (errores.isEmpty()) println("Sin errores")
        else errores.forEach { println("  ERROR: $it") }
    }
}

data class OrdenProduccion(
    override val id: String,
    val operador: String,
    val piezas: List<String>,
    val cantidad: Int
) : Serializable, Validable {

    override fun serializar() = "$id|$operador|${piezas.joinToString(",")}|$cantidad"

    override val errores: List<String> get() = buildList {
        if (operador.isBlank()) add("El operador no puede estar vacio")
        if (piezas.isEmpty()) add("La orden debe tener al menos una pieza")
        if (cantidad <= 0) add("La cantidad debe ser mayor que cero")
    }

    override fun validar() = esValido
}

fun main() {
    val orden1 = OrdenProduccion("O-001", "Juan", listOf("Engranaje", "Eje"), 50)
    val orden2 = OrdenProduccion("O-002", "", emptyList(), -5)

    fun procesarSerializable(s: Serializable) = println("-> ${s.serializar()}")
    fun procesarValidable(v: Validable) {
        println("Valido: ${v.esValido}")
        v.imprimirErrores()
    }

    procesarSerializable(orden1)
    procesarValidable(orden1)
    procesarValidable(orden2)
}
