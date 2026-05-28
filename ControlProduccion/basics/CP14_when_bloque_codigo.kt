fun main() {
    println("=== Alertas de Produccion ===")
    println("Nombre del operador:")
    val operador = readLine()?.trim() ?: ""
    println("Nivel de alerta CRITICO/ALTO/MEDIO/BAJO:")
    val nivel = readLine()?.trim()?.uppercase() ?: ""

    when (nivel) {
        "CRITICO" -> {
            println("ALERTA CRITICA - Operador: $operador")
            println("Detener linea de produccion")
            println("Notificar a supervisor inmediato")
        }
        "ALTO" -> {
            println("ALTO - Operador: $operador")
            println("Reducir velocidad de produccion")
            println("Revisar en 15 minutos")
        }
        "MEDIO" ->
            println("Medio - Operador: $operador, monitorear y reportar")

        "BAJO" ->
            println("Bajo - Operador: $operador, continuar con supervision normal")

        else ->
            println("Nivel de alerta no reconocido")
    }
    println("Antiguedad (años):")
    val antiguedad = readLine()?.toIntOrNull() ?: 0
    val categoria = when (antiguedad) {
        in 0..1 -> "Novato"
        in 2..3 -> "Aprendiz"
        in 4..7 -> "Experimentado"
        in 8..15 -> "Veterano"
        else -> "Maestro"
    }
    println("$antiguedad años -> $categoria")
}
