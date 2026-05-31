class LibroBiblioteca(
    val id: Int,
    val titulo: String,
    val autor: String
) {
    var disponible: Boolean = true
        private set

    fun prestar(): Boolean {
        return if (disponible) {
            disponible = false
            true
        } else {
            false
        }
    }

    fun devolver(): Boolean {
        return if (!disponible) {
            disponible = true
            true
        } else {
            false
        }
    }

    fun mostrarInfo() {
        val estado = if (disponible) "Disponible" else "Prestado"
        println("ID: $id | $titulo - $autor | $estado")
    }
}

class Biblioteca {
    private val libros = mutableListOf<LibroBiblioteca>()
    private var nextId = 1

    fun registrarLibro(titulo: String, autor: String) {
        val libro = LibroBiblioteca(nextId, titulo, autor)
        libros.add(libro)
        println("Libro registrado: $titulo (ID: $nextId)")
        nextId++
    }

    fun listarDisponibles() {
        println("=== Libros Disponibles ===")
        val disponibles = libros.filter { it.disponible }
        if (disponibles.isEmpty()) {
            println("No hay libros disponibles.")
        } else {
            for (libro in disponibles) {
                libro.mostrarInfo()
            }
        }
        println()
    }

    fun prestarLibro(id: Int) {
        val libro = libros.find { it.id == id }
        if (libro == null) {
            println("Error: No existe un libro con ID $id")
        } else if (libro.prestar()) {
            println("Libro \"${libro.titulo}\" prestado exitosamente.")
        } else {
            println("Error: El libro \"${libro.titulo}\" ya esta prestado.")
        }
    }

    fun devolverLibro(id: Int) {
        val libro = libros.find { it.id == id }
        if (libro == null) {
            println("Error: No existe un libro con ID $id")
        } else if (libro.devolver()) {
            println("Libro \"${libro.titulo}\" devuelto exitosamente.")
        } else {
            println("Error: El libro \"${libro.titulo}\" no estaba prestado.")
        }
    }

    fun buscarPorTitulo(titulo: String) {
        val resultados = libros.filter {
            it.titulo.lowercase().contains(titulo.lowercase())
        }
        if (resultados.isEmpty()) {
            println("No se encontraron libros con \"$titulo\"")
        } else {
            println("=== Resultados de busqueda: \"$titulo\" ===")
            for (libro in resultados) {
                libro.mostrarInfo()
            }
        }
        println()
    }

    fun listarTodos() {
        println("=== Todos los libros ===")
        if (libros.isEmpty()) {
            println("No hay libros registrados.")
        } else {
            for (libro in libros) {
                libro.mostrarInfo()
            }
        }
        println()
    }
}

fun main() {
    val biblioteca = Biblioteca()

    biblioteca.registrarLibro("Cien anios de soledad", "Gabriel Garcia Marquez")
    biblioteca.registrarLibro("1984", "George Orwell")
    biblioteca.registrarLibro("El principito", "Antoine de Saint-Exupery")
    biblioteca.registrarLibro("Don Quijote de la Mancha", "Miguel de Cervantes")
    println()

    biblioteca.listarTodos()
    biblioteca.listarDisponibles()

    biblioteca.prestarLibro(1)
    biblioteca.prestarLibro(3)
    biblioteca.prestarLibro(1)
    println()

    biblioteca.listarDisponibles()

    biblioteca.devolverLibro(1)
    biblioteca.devolverLibro(5)
    biblioteca.devolverLibro(1)
    println()

    biblioteca.listarDisponibles()
    biblioteca.buscarPorTitulo("quijote")
    biblioteca.buscarPorTitulo("harry")
}
