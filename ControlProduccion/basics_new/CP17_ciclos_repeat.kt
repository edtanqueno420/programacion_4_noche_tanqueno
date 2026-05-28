fun main() {
    println("CICLOS repeat - Control de Calidad")

    println("Cuantas muestras tomar para control de calidad?")
    val muestras = readLine()?.toIntOrNull() ?: 3

    var totalMediciones = 0

    repeat(muestras) { i ->
        println("Muestra ${i + 1} (peso en gramos):")
        val peso = readLine()?.toDoubleOrNull() ?: 0.0
        totalMediciones += peso.toInt()
    }

    val promedio = totalMediciones / muestras

    println("Peso promedio: $promedio g")

    println("Clasificacion: ${
        when {
            promedio < 80 -> "Por debajo del estandar"
            promedio <= 120 -> "Estandar"
            else -> "Sobrepeso"
        }
    }")
}
