fun main() {
    println("=== Conversion de Datos de Produccion ===")
    val piezas: Int = 150

    val pesoTotal: Double = piezas.toDouble()
    val lote: Long = piezas.toLong()
    val reporte: String = piezas.toString()

    println("toDouble $pesoTotal")
    println("toLong $lote")
    println("toString $reporte")

    println("String a Numerico")
    val codigoLote = "4501".toInt()
    val temperatura = "98.5".toDouble()

    val invalido = "abc".toIntOrNull()
    println(invalido)
}
