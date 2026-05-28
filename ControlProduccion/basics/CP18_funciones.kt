fun main() {
    saludar()
    saludarConParametros("Operario Juan")
    val piezas1 = 150
    val piezas2 = 80
    println("Total producido: ${sumar(piezas1, piezas2)}")
    println("Diferencia: ${restar(piezas1, piezas2)}")
    println("Piezas por hora (8h): ${multiplicar(piezas1, 8)}")
}

fun saludar() {
    println("Bienvenido al sistema de produccion")
}

fun saludarConParametros(nombre: String) {
    println("Buenas tardes: $nombre")
}

fun sumar(numero1: Int, numero2: Int): Int {
    return numero1 + numero2
}

fun restar(numero1: Int, numero2: Int) = numero1 - numero2

fun operaciones() {
    fun cuadrado(x: Int) = x * x
    println(cuadrado(5))
}

val multiplicar = { a: Int, b: Int -> a * b }
