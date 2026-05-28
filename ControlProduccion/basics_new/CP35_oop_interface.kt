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
        if (errores.isEmpty()) println("Sin errores")
        else errores.forEach { println("  Error: $it") }
    }
}

data class OrdenProduccion(
    override val id: String,
    val producto:     String,
    val materiales:       List<String>,
    val cantidad:       Double
) : Codificable, ValidableProduccion {

    override fun codificar() =
        "$id|$producto|${materiales.joinToString(",")}|$cantidad"

    override val errores: List<String> get() = buildList {
        if (producto.isBlank()) add("El producto no puede estar vacio")
        if (materiales.isEmpty())   add("La orden debe tener al menos un material")
        if (cantidad <= 0)        add("La cantidad debe ser mayor que cero")
    }

    override fun validar() = esValido
}

fun main() {
    val orden1 = OrdenProduccion("OP-001", "Silla", listOf("Madera", "Tornillos"), 150.0)
    val orden2 = OrdenProduccion("OP-002", "",    emptyList(),                -5.0)

    fun procesarCodificable(s: Codificable) = println(" -> ${s.codificar()}")
    fun procesarValidable(v: ValidableProduccion) {
        println("Valido: ${v.esValido}")
        v.imprimirErrores()
    }

    procesarCodificable(orden1)
    procesarValidable(orden1)
    procesarValidable(orden2)
}
