enum class EstadoProduccion(val descripcion: String, val esTerminal: Boolean) {
    PLANIFICADA("Orden planificada en espera", false),
    EN_CURSO("Produccion en ejecucion", false),
    COMPLETADA("Produccion finalizada con exito", true),
    RECHAZADA("Lote rechazado por calidad", true),
    CANCELADA("Orden cancelada por el cliente", true);

    fun puedeTransicionarA(siguiente: EstadoProduccion): Boolean = when (this) {
        PLANIFICADA -> siguiente == EN_CURSO || siguiente == CANCELADA
        EN_CURSO -> siguiente == COMPLETADA || siguiente == RECHAZADA
        else -> false
    }
}

fun main() {
    val estado = EstadoProduccion.EN_CURSO
    println(estado.descripcion)
    println(estado.esTerminal)

    val icono = when (estado) {
        EstadoProduccion.PLANIFICADA -> "PLAN"
        EstadoProduccion.EN_CURSO -> "EJEC"
        EstadoProduccion.COMPLETADA -> "OK"
        EstadoProduccion.RECHAZADA -> "RECHAZ"
        EstadoProduccion.CANCELADA -> "CANC"
    }
    println(icono)

    println(estado.puedeTransicionarA(EstadoProduccion.COMPLETADA))
}
