fun main() {
    println("=== Prioridad de Maquina ===")
    println("Maquina presenta falla critica? (s/n)")
    val fallaCritica = readLine()?.trim()?.lowercase() == "s"
    println("Maquina detenida? (s/n)")
    val detenida = readLine()?.trim()?.lowercase() == "s"
    println("Temperatura del motor:")
    val temperatura = readLine()?.toDoubleOrNull() ?: 80.0

    val prioridad = when {
        fallaCritica && detenida -> "PARO TOTAL - ATENCION INMEDIATA"
        fallaCritica -> "ALTA PRIORIDAD - Reparacion urgente"
        temperatura >= 120.0 -> "PRIORIDAD MEDIA - Monitoreo constante"
        temperatura >= 95.0 -> "PRIORIDAD BAJA - Revision programada"
        else -> "OPERACION NORMAL"
    }
    println("Resultado: $prioridad")
}
