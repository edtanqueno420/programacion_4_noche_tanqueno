enum class EstadoProduccion(val descripcion: String, val esTerminal: Boolean) {
    PENDIENTE("Esperando inicio", false),
    EN_PROCESO("En fabricacion", false),
    COMPLETADO("Finalizado con exito", true),
    RECHAZADO("Finalizado con defectos", true),
    CANCELADO("Cancelado por el cliente", true);

    fun puedeTransicionarA(siguiente: EstadoProduccion): Boolean = when (this) {
        PENDIENTE -> siguiente == EN_PROCESO || siguiente == CANCELADO
        EN_PROCESO -> siguiente == COMPLETADO || siguiente == RECHAZADO
        else -> false
    }
}

fun main() {
    val estado = EstadoProduccion.EN_PROCESO
    println(estado.descripcion)
    println(estado.esTerminal)

    val icono = when (estado) {
        EstadoProduccion.PENDIENTE -> "P"
        EstadoProduccion.EN_PROCESO -> "F"
        EstadoProduccion.COMPLETADO -> "C"
        EstadoProduccion.RECHAZADO -> "R"
        EstadoProduccion.CANCELADO -> "X"
    }
    println(icono)

    println(estado.puedeTransicionarA(EstadoProduccion.COMPLETADO))
}
