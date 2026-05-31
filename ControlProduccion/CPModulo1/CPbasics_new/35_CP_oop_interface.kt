interface Codificable {
    val id: String
    fun codificar(): String
    val version: Int get() = 1
}

interface ValidableProduccion {
    val errores: List<String>
    val esValido: Boolean get() = errores.isEmpty()

    fun validar(): Boolean
    fun imprimirErrores() {
        if (errores.isEmpty()) println("Sin errores de validacion")
        else errores.forEach { println("  ERROR: $it") }
    }
}

data class OrdenTrabajo(
    override val id: String,
    val pieza: String,
    val operarios: List<String>,
    val cantidad: Int
) : Codificable, ValidableProduccion {

    override fun codificar() =
        "$id|$pieza|${operarios.joinToString(",")}|$cantidad"

    override val errores: List<String> get() = buildList {
        if (pieza.isBlank()) add("La pieza no puede estar vacia")
        if (operarios.isEmpty()) add("Debe tener al menos un operario asignado")
        if (cantidad <= 0) add("La cantidad debe ser mayor que cero")
    }

    override fun validar() = esValido
}

fun main() {
    val orden1 = OrdenTrabajo("OT-001", "Eje principal", listOf("Carlos", "Luis"), 500)
    val orden2 = OrdenTrabajo("OT-002", "", emptyList(), -5)

    fun procesarCodificable(c: Codificable) = println("-> ${c.codificar()}")
    fun procesarValidable(v: ValidableProduccion) {
        println("Valido: ${v.esValido}")
        v.imprimirErrores()
    }

    procesarCodificable(orden1)
    procesarValidable(orden1)
    procesarValidable(orden2)
}
