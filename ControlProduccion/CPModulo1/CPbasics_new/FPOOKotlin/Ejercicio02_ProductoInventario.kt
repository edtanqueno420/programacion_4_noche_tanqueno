class ProductoInventario(
    val codigo: String,
    val nombre: String
) {
    private var precio: Double = 0.0
    private var stock: Int = 0

    constructor(codigo: String, nombre: String, precio: Double, stock: Int) : this(codigo, nombre) {
        if (precio >= 0) {
            this.precio = precio
        } else {
            println("Error: El precio no puede ser negativo. Se asigno 0.")
        }
        if (stock >= 0) {
            this.stock = stock
        } else {
            println("Error: El stock no puede ser negativo. Se asigno 0.")
        }
    }

    fun consultarPrecio(): Double {
        return precio
    }

    fun consultarStock(): Int {
        return stock
    }

    fun aumentarStock(cantidad: Int) {
        if (cantidad > 0) {
            stock += cantidad
            println("Stock aumentado en $cantidad. Stock actual: $stock")
        } else {
            println("Error: La cantidad a aumentar debe ser positiva.")
        }
    }

    fun disminuirStock(cantidad: Int) {
        if (cantidad > 0) {
            if (stock - cantidad >= 0) {
                stock -= cantidad
                println("Stock disminuido en $cantidad. Stock actual: $stock")
            } else {
                println("Error: No hay suficiente stock. Stock actual: $stock")
            }
        } else {
            println("Error: La cantidad a disminuir debe ser positiva.")
        }
    }

    fun cambiarPrecio(nuevoPrecio: Double) {
        if (nuevoPrecio >= 0) {
            precio = nuevoPrecio
            println("Precio actualizado a: $precio")
        } else {
            println("Error: El precio no puede ser negativo.")
        }
    }

    fun mostrarInfo() {
        println("Producto: $nombre (Codigo: $codigo)")
        println("Precio: $precio")
        println("Stock: $stock")
    }
}

fun main() {
    val producto1 = ProductoInventario("P001", "Perno Hexagonal M8", 0.45, 500)
    val producto2 = ProductoInventario("P002", "Tuerca M10", 0.25, 1000)
    val producto3 = ProductoInventario("P003", "Arandela de Presion", -1.0, -10)

    println("--- Estado inicial ---")
    producto1.mostrarInfo()
    println()
    producto2.mostrarInfo()
    println()
    producto3.mostrarInfo()
    println()

    println("--- Operaciones sobre producto1 ---")
    println("Precio actual: ${producto1.consultarPrecio()}")
    println("Stock actual: ${producto1.consultarStock()}")
    producto1.aumentarStock(200)
    producto1.disminuirStock(50)
    producto1.disminuirStock(800)
    producto1.cambiarPrecio(0.55)
    producto1.cambiarPrecio(-10.0)
    println()
    producto1.mostrarInfo()
}
