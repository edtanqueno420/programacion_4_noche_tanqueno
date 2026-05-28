data class CategoriaProd(val id: Int, val nombre: String)

data class ProductoManufacturado(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: CategoriaProd,
    val activo: Boolean = true
) {
    val disponible: Boolean get() = activo && stock > 0
    val precioConIva: Double get() = precio * 1.19

    fun aplicarDescuento(porcentaje: Double): ProductoManufacturado {
        require(porcentaje in 0.0..100.0) { "Descuento debe ser entre 0 y 100" }
        return copy(precio = precio * (1 - porcentaje / 100))
    }
}

object CatalogoProduccion {
    private val categorias = mutableListOf(
        CategoriaProd(1, "Engranajes"),
        CategoriaProd(2, "Ejes"),
        CategoriaProd(3, "Soportes")
    )
    private val productos = mutableListOf<ProductoManufacturado>()
    private var siguienteId = 1

    fun agregarProducto(nombre: String, precio: Double, stock: Int, categoriaId: Int): ProductoManufacturado? {
        val categoria = categorias.find { it.id == categoriaId } ?: return null
        val producto = ProductoManufacturado(siguienteId++, nombre, precio, stock, categoria)
        productos.add(producto)
        return producto
    }

    fun listar(): List<ProductoManufacturado> = productos.toList()
    fun disponibles(): List<ProductoManufacturado> = productos.filter { it.disponible }
    fun porCategoria(id: Int): List<ProductoManufacturado> = productos.filter { it.categoria.id == id }
    fun buscar(query: String): List<ProductoManufacturado> =
        productos.filter { it.nombre.contains(query, ignoreCase = true) }
}

fun main() {
    CatalogoProduccion.agregarProducto("Engranaje recto", 89.99, 15, 1)
    CatalogoProduccion.agregarProducto("Engranaje helicoidal", 129.99, 0, 1)
    CatalogoProduccion.agregarProducto("Eje de transmision", 349.99, 5, 2)
    CatalogoProduccion.agregarProducto("Soporte ajustable", 49.99, 8, 3)
    CatalogoProduccion.agregarProducto("Soporte pesado", 159.99, 5, 2)
    CatalogoProduccion.agregarProducto("Eje cardanico", 600.99, 8, 3)

    println("=== Todos los productos ===")
    CatalogoProduccion.listar().forEach { p ->
        val estado = if (p.disponible) "OK" else "NO"
        println("$estado ${p.nombre} -- ${"%.2f".format(p.precioConIva)} (con IVA)")
    }

    println("\n=== Disponibles con 15% descuento ===")
    CatalogoProduccion.disponibles()
        .map { it.aplicarDescuento(15.0) }
        .forEach { println("  ${it.nombre}: ${"%.2f".format(it.precio)}") }

    for (producto in CatalogoProduccion.listar()) {
        println(producto)
    }
}
