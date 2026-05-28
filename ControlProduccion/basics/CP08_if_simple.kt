fun main() {
    println("=== Monitoreo de Maquinaria ===")
    println("Temperatura del horno (grados C): ")
    val temperatura = readLine()?.toDoubleOrNull() ?: 100.0
    if (temperatura >= 250) {
        println("ALERTA: Sobrecalentamiento detectado")
    }
    if (temperatura >= 400) {
        println("PELIGRO: Temperatura critica - Detener maquina")
    }
    println("Temperatura registrada: $temperatura")
}
