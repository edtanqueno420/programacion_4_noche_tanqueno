data class CategoriaMaterial(val id: Int, val nombre: String)

data class MaterialProducto(
    val id:        Int,
    val nombre:    String,
    val precio:    Double,
    val stock:     Int,
    val categoria: CategoriaMaterial,
    val activo:    Boolean = true
) {
    val disponible: Boolean get() = activo && stock > 0
    val precioConIva: Double get() = precio * 1.19

    fun aplicarDescuento(porcentaje: Double): MaterialProducto {
        require(porcentaje in 0.0..100.0) { "Descuento debe ser entre 0 y 100" }
        return copy(precio = precio * (1 - porcentaje / 100))
    }
}

object CatalogoMateriales {
    private val categorias = mutableListOf(
        CategoriaMaterial(1, "Metal"),
        CategoriaMaterial(2, "Plastico"),
        CategoriaMaterial(3, "Madera")
    )
    private val materiales   = mutableListOf<MaterialProducto>()
    private var siguienteId = 1

    fun agregarMaterial(nombre: String, precio: Double, stock: Int, categoriaId: Int): MaterialProducto? {
        val categoria = categorias.find { it.id == categoriaId } ?: return null
        val material  = MaterialProducto(siguienteId++, nombre, precio, stock, categoria)
        materiales.add(material)
        return material
    }

    fun listar(): List<MaterialProducto>              = materiales.toList()
    fun disponibles(): List<MaterialProducto>         = materiales.filter { it.disponible }
    fun porCategoria(id: Int): List<MaterialProducto> = materiales.filter { it.categoria.id == id }
    fun buscar(query: String): List<MaterialProducto> =
        materiales.filter { it.nombre.contains(query, ignoreCase = true) }
}

fun main() {
    CatalogoMateriales.agregarMaterial("Acero Laminado",   89.99, 150, 1)
    CatalogoMateriales.agregarMaterial("Aluminio",  45.50,  0, 1)
    CatalogoMateriales.agregarMaterial("Polipropileno",      12.75,  500, 2)
    CatalogoMateriales.agregarMaterial("Madera Roble",    29.99,  80, 3)
    CatalogoMateriales.agregarMaterial("Madera Pino", 15.50,  200, 3)
    CatalogoMateriales.agregarMaterial("PVC Industrial",    8.99,  1000, 2)

    println("=== Todos los materiales ===")
    CatalogoMateriales.listar().forEach { m ->
        val estado = if (m.disponible) "Disponible" else "Agotado"
        println("$estado ${m.nombre} — ${"%.2f".format(m.precioConIva)} (con IVA)")
    }

    println("\n=== Disponibles con 10% descuento ===")
    CatalogoMateriales.disponibles()
        .map { it.aplicarDescuento(10.0) }
        .forEach { println("  ${it.nombre}: ${"%.2f".format(it.precio)}") }

    for (material in CatalogoMateriales.listar()){
        println(material)

    }

}
