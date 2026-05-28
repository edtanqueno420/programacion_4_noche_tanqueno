
fun main() {
    val lineaProduccion="Linea A"
    val turno: Int = 1
    var piezasProducidas=0
    piezasProducidas = piezasProducidas+150

    println("$lineaProduccion - Turno $turno produjo $piezasProducidas piezas")

    val codigoMaquina: Byte = 127
    val temperaturaHorno: Short = 450
    val lote: Int = 1001
    val codigoBarras: Long = 7_901_234_567_890

    println(codigoMaquina)
    println(temperaturaHorno)
    println(lote)
    println(codigoBarras)

    val peso: Float=24.5f
    val precioUnitario: Double=15.99

    val enProduccion: Boolean=true

    val categoria: Char ='A'
    val material: String="Acero Inoxidable"

    val primeraLinea="Edison"
    val ultimaLinea="Tanqueno"
    val primeraMayus=primeraLinea.uppercase()
    val ultimaMayus=ultimaLinea.uppercase()

    println("Operario: ${primeraMayus} ${ultimaMayus}")
    println("Operario: ${primeraLinea.uppercase()} ${ultimaLinea.uppercase()}")
}
