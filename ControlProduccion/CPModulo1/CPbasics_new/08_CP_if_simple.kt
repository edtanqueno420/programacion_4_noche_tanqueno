
fun main() {
    println("Control de Calidad")
    println("If Simple")
    println("Tolerancia de la pieza en mm:")
    val tolerancia = readLine()?.toDoubleOrNull() ?: 0.5
    if (tolerancia >= 1.0) {
        println("Pieza fuera de especificacion")
    }
    if (tolerancia >= 2.0) {
        println("Pieza rechazada automaticamente")
    }
    println("Tolerancia registrada: $tolerancia mm")
}
