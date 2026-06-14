fun main() {
    println("Diagnostico de maquina")
    println("La maquina tiene programa de mantenimiento preventivo? s/n")
    val tienePlan = readLine()?.trim()?.lowercase() == "s"
    println("Temperatura del motor (grados C): ")
    val temperatura = readLine()?.toIntOrNull() ?: 0
    if (tienePlan) {
        println("Maquina con plan de mantenimiento")
        if (temperatura < 60) {
            println("Temperatura normal - Operacion segura")
        } else if (temperatura > 95) {
            println("ALERTA - Sobrecalentamiento critico")
        } else {
            println("Temperatura dentro del rango operativo")
        }
    } else {
        println("Maquina SIN plan de mantenimiento")
        if (temperatura < 50 || temperatura > 85) {
            println("Temperatura fuera de rango seguro - Detener maquina")
        } else {
            println("Temperatura de operacion aceptable")
        }
    }
}
