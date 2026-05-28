data class Material(
    val id:        Int,
    val nombre:    String,
    val precioUnitario:    Double,
    val tipo: String,
    val disponible:    Boolean = true
)

fun main() {
    val m1 = Material(1, "Acero Laminado", 89.99, "Metal")
    val m2 = Material(2, "Aluminio", 45.50, "Metal")
    val m3 = Material(3, "Polipropileno", 12.75, "Plastico")

    println(m1)

    val barato   = m1.copy(precioUnitario = 59.99)
    val agotado = m1.copy(disponible = false)

    val (id, nombre, precio) = m1
    println("$id: $nombre — $$precio")

    listOf(m1, m3).forEach { (id2, nombre2, precio2) ->
        println("[$id2] $nombre2: $$precio2")
    }
}
