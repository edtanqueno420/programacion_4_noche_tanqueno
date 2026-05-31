
fun main() {
    val codigoPieza = "PZ-001"
    val cantidad: Int = 500
    var stock = 120
    stock = stock - 15

    println("$codigoPieza tiene $cantidad unidades en produccion")

    val toleranciaMm: Byte = 2
    val rpmMaximas: Short = 12_000
    val lotesMes: Int = 340
    val costoProduccion: Long = 15_000_000_000

    println(toleranciaMm)
    println(rpmMaximas)
    println(lotesMes)
    println(costoProduccion)

    val pesoKg: Float = 12.5f
    val presionBar: Double = 6.8342

    val certificadoISO: Boolean = true

    val categoriaMaquina: Char = 'A'
    val material: String = "Acero Inoxidable"
    val inferido = "CNC"

    println("Tipo de inferido: ${inferido::class.simpleName}")

    val nombrePieza = "Eje"
    val codigoMaterial = "AISI-304"
    val nombrePiezaMayus = nombrePieza.uppercase()
    val codigoMaterialMayus = codigoMaterial.uppercase()

    println("Pieza: ${nombrePiezaMayus} - Material: ${codigoMaterialMayus}")
    println("Pieza: ${nombrePieza.uppercase()} - Material: ${codigoMaterial.uppercase()}")
}
