fun main() {
    //Val Inmutable
    val nombrePlanta = "Planta Central"
    val turno: Int = 1
    //Var mutable
    var piezasProducidas = 0
    piezasProducidas = piezasProducidas + 150

    println("$nombrePlanta - Turno $turno: $piezasProducidas piezas")

    //Tipos de datos
    val calidad: Byte = 100
    val operarios: Short = 250
    val codigoMaquina: Int = 4501
    val serieLote: Long = 2_222_022_333_222_333L

    println(calidad)
    println(operarios)
    println(codigoMaquina)
    println(serieLote)

    // Numero decimales
    val peso: Float = 12.5f
    val precioUnitario: Double = 149.99

    val activo: Boolean = true

    //Caracteres
    val categoria: Char = 'A'
    val codigoProducto: String = "CP-4501-XYZ"
    val inferido = "produccion"

    println("Tipo de inferido: ${inferido::class.simpleName}")

    //Utilidades de String
    val material = "acero"
    val materialMayuscula = material.uppercase()
    println("Material: $materialMayuscula")
    println("Material: ${material.uppercase()}")
}
