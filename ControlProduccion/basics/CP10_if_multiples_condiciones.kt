fun main() {
    println("=== Eficiencia de Produccion ===")
    println("Piezas producidas por hora: ")
    val piezas = readLine()?.toIntOrNull() ?: 0
    val clasificacion = if (piezas < 30) {
        "Baja eficiencia"
    } else if (piezas <= 60) {
        "Eficiencia normal"
    } else if (piezas <= 90) {
        "Alta eficiencia"
    } else if (piezas <= 120) {
        "Eficiencia excelente"
    } else {
        "Sobreproduccion - Revisar proceso"
    }
    println("Clasificacion: $clasificacion")
}
