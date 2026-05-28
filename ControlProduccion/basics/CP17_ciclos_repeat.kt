fun main() {
    println("=== Control de Calidad - Muestreo ===")
    println("Cuantas piezas desea inspeccionar?")
    val muestras = readLine()?.toIntOrNull() ?: 3

    var totalDefectos = 0

    repeat(muestras) { i ->
        println("Pieza ${i + 1} - Peso (g):")
        val peso = readLine()?.toIntOrNull() ?: 0
        if (peso < 90 || peso > 110) totalDefectos++
    }

    val calidad = ((muestras - totalDefectos).toDouble() / muestras * 100).toInt()
    println("Calidad: $calidad% aceptacion")

    println("Clasificacion: ${
        when {
            calidad >= 95 -> "Excelente"
            calidad >= 80 -> "Aceptable"
            else -> "Requiere mejora"
        }
    }")
}
