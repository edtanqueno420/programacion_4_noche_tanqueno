fun main() {
    val produccionReal = 120
    val produccionMeta = 100

    println("=== Comparacion de Produccion ===")
    println("produccionReal==produccionMeta ${produccionReal==produccionMeta}")
    println("produccionReal!=produccionMeta ${produccionReal!=produccionMeta}")
    println("produccionReal>produccionMeta ${produccionReal>produccionMeta}")
    println("produccionReal<produccionMeta ${produccionReal<produccionMeta}")
    println("produccionReal>=produccionMeta ${produccionReal>=produccionMeta}")
    println("produccionReal<=produccionMeta ${produccionReal<=produccionMeta}")

    var codigo1 = "LOTE-001"
    var codigo2 = "LOTE-001"
    println("codigo1===codigo2 ${codigo1===codigo2}")
    println("codigo1==codigo2 ${codigo1==codigo2}")
    println("codigo1.equals(codigo2) ${codigo1.equals(codigo2)}")
}
