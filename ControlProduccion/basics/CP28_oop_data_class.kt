data class Pieza(
    val id: Int,
    val nombre: String,
    val peso: Double,
    val material: String,
    val activo: Boolean = true
)

fun main() {
    val p1 = Pieza(1, "Engranaje recto", 0.85, "Acero")
    val p2 = Pieza(2, "Engranaje helicoidal", 0.85, "Acero")
    val p3 = Pieza(3, "Eje de transmision", 2.50, "Hierro")

    println(p1)
    println(p1 == p2)
    println(p1 == p3)

    val ligero = p1.copy(peso = 0.45)
    val inactivo = p1.copy(activo = false)

    val (id, nombre, peso) = p1
    println("$id: $nombre -- ${peso}kg")

    listOf(p1, p3).forEach { (id2, nombre2, peso2) ->
        println("[$id2] $nombre2: ${peso2}kg")
    }
}
