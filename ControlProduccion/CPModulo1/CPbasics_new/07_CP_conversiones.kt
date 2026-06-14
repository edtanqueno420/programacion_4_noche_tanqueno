
fun main() {
    println("Conversiones en Control de Produccion")
    val piezasEntero: Int = 500

    val piezasDecimal: Double = piezasEntero.toDouble()
    val stockLong: Long = piezasEntero.toLong()
    val codigoString: String = piezasEntero.toString()

    println("to Double $piezasDecimal")
    println("to Long $stockLong")
    println("to String $codigoString")

    println("String a Numerico")
    val cantidad = "1234".toInt()
    val tolerancia = "0.15".toDouble()

    val invalido = "abcd".toIntOrNull()
    println(invalido)
}
