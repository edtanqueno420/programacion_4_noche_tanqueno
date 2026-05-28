class ProductoFactory(val codigo: String, val descripcion: String) {
    val codigoNormalizado: String
    val tipoMaterial: String

    init {
        require(codigo.isNotBlank()) { "El codigo no puede estar vacio" }
        require(descripcion.length >= 5) { "Descripcion muy corta: $descripcion" }

        codigoNormalizado = codigo.trim().uppercase()
        tipoMaterial = descripcion.substringAfter("-")
    }
}

fun main() {
    val p = ProductoFactory("  T-4501  ", "Acero-Inoxidable")
    println(p.codigoNormalizado)
    println(p.tipoMaterial)
}
