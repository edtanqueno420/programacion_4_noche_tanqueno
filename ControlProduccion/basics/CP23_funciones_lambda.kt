fun main() {
    println("=== Funcion Lambda - Produccion ===")
    val suma1: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    println("Suma: ${suma1(150, 80)}")

    val suma2: (Int, Int) -> Int = { a, b -> a + b }
    println("Suma: ${suma2(200, 100)}")

    val duplicar: (Int) -> Int = { it * 2 }
    println("Doble de 250: ${duplicar(250)}")
}
