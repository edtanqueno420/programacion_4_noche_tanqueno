fun main() {
    println("Triaje de paciente")

    println("Tiene dolor de pecho? (s/n)")
    val dolorPecho = readLine()?.trim()?.lowercase() == "s"

    println("Tiene dificultad respiratoria? (s/n)")
    val dificultad = readLine()?.trim()?.lowercase() == "s"

    println("Ingrese la temperatura:")
    val temperatura = readLine()?.toDoubleOrNull() ?: 36.5

    val prioridad = when {
        dolorPecho && dificultad -> "ALTA PRIORIDAD"
        dolorPecho -> "PRIORIDAD MEDIA-ALTA"
        temperatura >= 39.5 -> "PRIORIDAD MEDIA"
        temperatura >= 38.0 -> "PRIORIDAD BAJA"
        else -> "PRIORIDAD NORMAL"
    }

    println("Resultado: $prioridad")
}