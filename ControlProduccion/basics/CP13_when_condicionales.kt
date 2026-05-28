fun main() {
    println("=== Calculo de Horas Extra ===")
    println("Horas trabajadas en el turno:")
    val horas = readLine()?.toIntOrNull() ?: 0
    println("Es operario calificado? s/n")
    val calificado = readLine()?.trim()?.lowercase() == "s"

    val nivelOperario = if (calificado) {
        println("Nivel del operario (Junior/Intermedio/Senior):")
        readLine()?.trim()?.uppercase() ?: ""
    } else ""

    val pagoHora = when {
        !calificado && horas < 8 -> 15.0
        !calificado && horas >= 12 -> 25.0
        !calificado -> 18.0
        nivelOperario == "JUNIOR" -> 20.0
        nivelOperario == "INTERMEDIO" -> 30.0
        nivelOperario == "SENIOR" -> 40.0
        else -> 22.0
    }
    println("Pago por hora extra: $${"%.2f".format(pagoHora)}")
}
