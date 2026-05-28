fun main() {
    println("=== Utilidades de Listas - Produccion ===")
    val piezas = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
    println(piezas)
    val pesos = piezas.map { it * 2 }
    println(pesos)
    val etiquetas = piezas.map { "Pieza-$it" }
    println(etiquetas)

    println("Filter")
    val mayores50 = piezas.filter { it > 50 }
    println(mayores50)
    val pares = piezas.filter { it % 2 == 0 }
    println(pares)
    val entre30y70 = piezas.filter { it > 30 && it < 70 }
    println(entre30y70)
    val impares = piezas.filterNot { it % 2 == 0 }
    println(impares)
}
