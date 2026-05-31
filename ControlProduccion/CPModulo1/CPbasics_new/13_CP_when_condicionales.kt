fun main() {
    println("When con Condiciones - Costos de Produccion")
    println("Cantidad de piezas a fabricar:")
    val cantidad = readLine()?.toIntOrNull() ?: 0
    println("Material certificado? s/n")
    val materialCertificado = readLine()?.trim()?.lowercase() == "s"

    val nivelMaterial = if (materialCertificado) {
        println("Nivel del material (Estandar/Plus/Premium)")
        readLine()?.trim()?.uppercase() ?: ""
    } else ""

    val descuento = when {
        !materialCertificado && cantidad < 100 -> 0.0
        !materialCertificado && cantidad >= 500 -> 12.0
        !materialCertificado -> 5.0
        nivelMaterial == "ESTANDAR" -> 8.0
        nivelMaterial == "PLUS" -> 15.0
        nivelMaterial == "PREMIUM" -> 25.0
        else -> 3.0
    }
    println("Descuento aplicado: $${"%.2f".format(descuento)} por pieza")
}
