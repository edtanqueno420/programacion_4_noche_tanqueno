fun main() {
    println("Control de flujo")
    println("If Dos Caminos")
    println("Pieza certificada ISO? s/n")
    val certificada = readLine()?.trim()?.lowercase() == "s"
    println("Costo base de fabricacion? $")
    val costoBase = readLine()?.toDoubleOrNull() ?: 0.0
    if (certificada) {
        val sobrecosto = costoBase * 0.15
        println("Certificacion agrega $sobrecosto Total fabricacion: ${costoBase + sobrecosto}")
    } else {
        println("Costo de fabricacion: $costoBase")
    }
}
