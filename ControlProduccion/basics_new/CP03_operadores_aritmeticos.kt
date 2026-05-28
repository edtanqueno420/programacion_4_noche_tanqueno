
fun main() {

    val piezasProducidas=150
    val piezasDefectuosas=5

    println("Suma")
    println("piezasProducidas+piezasDefectuosas=${piezasProducidas+piezasDefectuosas}")
    println("Resta")
    println("piezasProducidas-piezasDefectuosas= ${piezasProducidas-piezasDefectuosas}")
    println("Multiplicacion")
    println("piezasProducidas*precioUni...")

    println("piezasProducidas*2= ${piezasProducidas*2}")
    println("Division")
    println("piezasProducidas/turno= ${piezasProducidas/2}")
    println("Modulo")
    println("piezasProducidas%3= ${piezasProducidas%3}")

    println("Operadores de Asignacion Compuestas")
    var inventario=100
    inventario+=50
    println("inventario+=50 ${inventario}")
    inventario-=20
    println("inventario-=20 ${inventario}")
    inventario*=2
    println("inventario*=2 ${inventario}")
    inventario/=4
    println("inventario/=4 ${inventario}")
    inventario%=3
    println("inventario%=3 ${inventario}")

    var contador=0
    contador++
    println("contador++ ${contador}")
    contador--
    println("contador-- ${contador}")
}
