data class CategoriaProd(val id: Int, val nombre: String)

data class ProductoManufactura(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: CategoriaProd,
    val activo: Boolean = true
) {
    val disponible: Boolean get() = activo && stock > 0
    val precioConIva: Double get() = precio * 1.19

    fun aplicarDescuento(porcentaje: Double): ProductoManufactura {
        require(porcentaje in 0.0..100.0) { "Descuento debe ser entre 0 y 100" }
        return copy(precio = precio * (1 - porcentaje / 100))
    }
}

object CatalogoPiezas {
    private val categorias = mutableListOf(
        CategoriaProd(1, "Ejes"),
        CategoriaProd(2, "Engranajes"),
        CategoriaProd(3, "Rodamientos")
    )
    private val productos = mutableListOf<ProductoManufactura>()
    private var siguienteId = 1

    fun agregarProducto(nombre: String, precio: Double, stock: Int, categoriaId: Int): ProductoManufactura? {
        val categoria = categorias.find { it.id == categoriaId } ?: return null
        val producto = ProductoManufactura(siguienteId++, nombre, precio, stock, categoria)
        productos.add(producto)
        return producto
    }

    fun listar(): List<ProductoManufactura> = productos.toList()
    fun disponibles(): List<ProductoManufactura> = productos.filter { it.disponible }
    fun porCategoria(id: Int): List<ProductoManufactura> = productos.filter { it.categoria.id == id }
    fun buscar(query: String): List<ProductoManufactura> =
        productos.filter { it.nombre.contains(query, ignoreCase = true) }
}

fun main() {
    CatalogoPiezas.agregarProducto("Eje acero templado", 89.99, 15, 1)
    CatalogoPiezas.agregarProducto("Buje bronce", 29.99, 0, 3)
    CatalogoPiezas.agregarProducto("Engranaje helicoidal", 349.99, 5, 2)
    CatalogoPiezas.agregarProducto("Rodamiento sellado", 149.99, 8, 3)
    CatalogoPiezas.agregarProducto("Eje estriado", 59.99, 5, 1)
    CatalogoPiezas.agregarProducto("Engranaje recto", 600.99, 8, 2)

    println("=== Todos los productos ===")
    CatalogoPiezas.listar().forEach { p ->
        val estado = if (p.disponible) "OK" else "SIN STOCK"
        println("$estado ${p.nombre} -- ${"%.2f".format(p.precioConIva)} (con IVA)")
    }

    println("\n=== Disponibles con 15% descuento ===")
    CatalogoPiezas.disponibles()
        .map { it.aplicarDescuento(15.0) }
        .forEach { println("  ${it.nombre}: ${"%.2f".format(it.precio)}") }

    for (productos in CatalogoPiezas.listar()) {
        println(productos)
    }
}
