fun main() {
    println("=== Control de Calidad ===")
    println("Pasa inspeccion visual? s/n")
    val pasaInspeccion = readLine()?.trim()?.lowercase() == "s"
    println("Peso de la pieza (g): ")
    val peso = readLine()?.toDoubleOrNull() ?: 0.0
    if (pasaInspeccion) {
        val descuento = peso * 0.05
        println("Pieza aceptada - Tolerancia: $descuento g")
    } else {
        println("Pieza rechazada - Peso: $peso g")
    }
}
