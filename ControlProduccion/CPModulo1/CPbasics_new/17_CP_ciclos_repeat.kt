fun main() {
    println("CICLOS repeat - Muestreo de Calidad")

    println("Cuantas muestras tomar del lote para control de calidad:")
    val muestras = readLine()?.toIntOrNull() ?: 3

    var totalDefectos = 0

    repeat(muestras) { i ->
        println("Muestra ${i + 1} (piezas defectuosas encontradas):")
        val defectos = readLine()?.toIntOrNull() ?: 0
        totalDefectos += defectos
    }

    val promedioDefectos = totalDefectos.toDouble() / muestras

    println("Promedio de defectos por muestra: $promedioDefectos")

    println("Clasificacion del lote: ${
        when {
            promedioDefectos < 0.5 -> "Excelente - Calidad superior"
            promedioDefectos <= 2.0 -> "Aceptable - Cumple especificaciones"
            else -> "Rechazado - No cumple calidad minima"
        }
    }")
}
