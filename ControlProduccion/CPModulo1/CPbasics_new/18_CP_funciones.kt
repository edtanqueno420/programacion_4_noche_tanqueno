fun main() {
    iniciarProduccion()
    saludarOperario("Carlos")
    val piezasLote1 = 150
    val piezasLote2 = 230
    println("Total piezas lote1 + lote2 = ${sumarPiezas(piezasLote1, piezasLote2)}")
    println("Diferencia lote1 - lote2 = ${restarPiezas(piezasLote1, piezasLote2)}")
    println("Costo total = ${calcularCosto(piezasLote1, piezasLote2)}")
}

fun iniciarProduccion() {
    println("Iniciando linea de produccion")
}

fun saludarOperario(nombre: String) {
    println("Buenas noches operario: $nombre")
}

fun sumarPiezas(piezas1: Int, piezas2: Int): Int {
    return piezas1 + piezas2
}

fun restarPiezas(piezas1: Int, piezas2: Int) = piezas1 - piezas2

fun operaciones() {
    fun cuadrado(x: Int) = x * x
    println(cuadrado(5))
}

val calcularCosto = { a: Int, b: Int -> (a + b) * 45 }
