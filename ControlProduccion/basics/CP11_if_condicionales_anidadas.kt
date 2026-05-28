fun main() {
    println("=== Diagnostico de Maquinaria ===")
    println("La maquina tiene mantenimiento programado? s/n")
    val tieneMantenimiento = readLine()?.trim()?.lowercase() == "s"
    println("Horas de operacion continuas: ")
    val horas = readLine()?.toIntOrNull() ?: 0
    if (tieneMantenimiento) {
        println("Maquina con mantenimiento programado")
        if (horas < 50) {
            println("Estado: Optimo")
        } else if (horas > 100) {
            println("Estado: Requiere mantenimiento urgente")
        } else {
            println("Estado: Dentro del rango operativo")
        }
    } else {
        println("Maquina sin mantenimiento programado")
        if (horas < 40) {
            println("Operacion normal")
        } else {
            println("Programar mantenimiento preventivo")
        }
    }
}
