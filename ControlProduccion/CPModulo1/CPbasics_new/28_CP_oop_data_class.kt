data class Producto1(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val activo: Boolean = true
)

fun main() {
    val p1 = Producto1(1, "Eje de transmision", 89.99, "Transmision")
    val p2 = Producto1(2, "Buje de bronce", 12.50, "Rodamientos")
    val p3 = Producto1(3, "Engranaje helicoidal", 349.99, "Engranajes")

    println(p1)

    println(p1 == p2)
    println(p1 == p3)

    val barato = p1.copy(precio = 59.99)
    val inactivo = p1.copy(activo = false)

    val (id, nombre, precio) = p1
    println("$id: $nombre -- $$precio")

    listOf(p1, p3).forEach { (id2, nombre2, precio2) ->
        println("[$id2] $nombre2: $$precio2")
    }
}
