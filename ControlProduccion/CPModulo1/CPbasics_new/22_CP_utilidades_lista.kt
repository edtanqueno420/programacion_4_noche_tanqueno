fun main() {
    println("Utilidades de Listas - Analisis de Produccion")
    val lotes = listOf(100, 200, 150, 300, 250, 180, 400, 350, 120, 220)
    println(lotes)
    val dobles = lotes.map { it * 2 }
    println(dobles)
    val lotesTexto = lotes.map { "Lote$it" }
    println(lotesTexto)

    println("Filter")
    val mayores200 = lotes.filter { it > 200 }
    println(mayores200)
    val menores250 = lotes.filter { it < 250 }
    println(menores250)
    val entre150y300 = lotes.filter { it > 150 && it < 300 }
    println(entre150y300)
    val menoresIgual150 = lotes.filterNot { it > 150 }
    println(menoresIgual150)
}
