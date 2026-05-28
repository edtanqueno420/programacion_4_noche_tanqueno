fun main() {
    println("Utilidades de Listas - Control Produccion")
    val piezas = listOf(1,2,3,4,5,6,7,8,9,10)
    println(piezas)
    val cuadrados = piezas.map{it*it}
    println(cuadrados)
    val piezasTexto = piezas.map{"Lote$it"}
    println(piezasTexto)

    println("Filter")
    val pares = piezas.filter{it % 2==0}
    println(pares)
    val mayores5 = piezas.filter{it > 5}
    println(mayores5)
    val paresYMayores5 = piezas.filter{it % 2==0 && it > 5}
    println(paresYMayores5)
    val impares = piezas.filterNot{it % 2==0}
    println(impares)
}
