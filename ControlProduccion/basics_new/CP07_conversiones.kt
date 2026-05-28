
fun main() {
    println("Conversiones en Produccion")
    val piezas: Int = 150

    val pesoTotal: Double = piezas.toDouble()
    val codigoLote: Long = piezas.toLong()
    val reporte: String = piezas.toString()

    println("to Double $pesoTotal")
    println("to Long $codigoLote")
    println("to String $reporte")

    println("String a Numerico")
    val cantidad1 = "250".toInt()
    val precioCompra = "19.99".toDouble()

    val invalido = "ABC123".toIntOrNull()
    println(invalido)
}
