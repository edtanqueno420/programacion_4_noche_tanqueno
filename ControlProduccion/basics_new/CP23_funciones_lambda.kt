fun main() {
    println("Funcion Lambda - Produccion")
    val suma1: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    println("Suma lotes: ${suma1(150, 200)}")

    val suma2: (Int, Int) -> Int = { a, b -> a + b }
    println("Suma piezas: ${suma2(80, 45)}")

    val duplicar: (Int) -> Int = { it * 2 }
    println("Doble de produccion: ${duplicar(350)}")
}
