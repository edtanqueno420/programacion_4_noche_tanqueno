class Libro(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int,
    val numPaginas: Int
) {
    fun mostrarInfo() {
        println("Titulo: $titulo")
        println("Autor: $autor")
        println("Anio de publicacion: $anioPublicacion")
        println("Numero de paginas: $numPaginas")
        if (esAntiguo()) {
            println("Estado: Antiguo")
        } else {
            println("Estado: Reciente")
        }
    }

    fun esAntiguo(): Boolean {
        return anioPublicacion < 2000
    }
}

fun main() {
    val libro1 = Libro("Cien anios de soledad", "Gabriel Garcia Marquez", 1967, 417)
    val libro2 = Libro("El codigo Da Vinci", "Dan Brown", 2003, 656)
    val libro3 = Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 863)

    println("--- Libro 1 ---")
    libro1.mostrarInfo()
    println()
    println("--- Libro 2 ---")
    libro2.mostrarInfo()
    println()
    println("--- Libro 3 ---")
    libro3.mostrarInfo()
}
