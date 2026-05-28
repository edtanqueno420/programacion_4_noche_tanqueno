enum class EstadoProduccion(val descripcion: String, val esTerminal: Boolean) {
    PLANIFICADO  ("Pendiente de inicio", false),
    EN_EJECUCION ("En proceso de fabricacion", false),
    COMPLETADO   ("Finalizado con exito",    true),
    RECHAZADO    ("Rechazado por calidad",    true),
    PAUSADO      ("Pausado por supervisor",   true);

    fun puedeTransicionarA(siguiente: EstadoProduccion): Boolean = when (this) {
        PLANIFICADO  -> siguiente == EN_EJECUCION || siguiente == PAUSADO
        EN_EJECUCION -> siguiente == COMPLETADO || siguiente == RECHAZADO || siguiente == PAUSADO
        else       -> false
    }
}

fun main() {
    val estado = EstadoProduccion.EN_EJECUCION
    println(estado.descripcion)
    println(estado.esTerminal)

    val icono = when (estado) {
        EstadoProduccion.PLANIFICADO   -> "\u23F0"
        EstadoProduccion.EN_EJECUCION  -> "\u2699"
        EstadoProduccion.COMPLETADO    -> "\u2705"
        EstadoProduccion.RECHAZADO     -> "\u274C"
        EstadoProduccion.PAUSADO       -> "\u23F8"
    }
    println(icono)

    println(estado.puedeTransicionarA(EstadoProduccion.COMPLETADO))
}
