fun main() {
    println("Funcion Lambda - Manufactura")
    val sumaPiezas: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    println(sumaPiezas(150, 230))

    val restaStock: (Int, Int) -> Int = { a, b -> a - b }
    println(restaStock(500, 120))

    val duplicarLote: (Int) -> Int = { it * 2 }
    println(duplicarLote(300))
}
