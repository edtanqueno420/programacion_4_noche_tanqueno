
fun main() {
    val piezasProducidas = 150
    val piezasDefectuosas = 12

    println("Suma")
    println("piezasProducidas+piezasDefectuosas=${piezasProducidas+piezasDefectuosas}")
    println("Resta")
    println("piezasProducidas-piezasDefectuosas=${piezasProducidas-piezasDefectuosas}")
    println("Multiplicacion")
    println("costoUnitario*cantidad=${45*piezasProducidas}")
    println("Division")
    println("piezasProducidas/maquinas=${piezasProducidas/3}")
    println("Modulo")
    println("piezasProducidas%maquinas=${piezasProducidas%3}")

    println("Operadores de Asignacion Compuestas")
    var totalProduccion = 100
    totalProduccion += 50
    println("totalProduccion+=50 $totalProduccion")
    totalProduccion -= 20
    println("totalProduccion-=20 $totalProduccion")
    totalProduccion *= 2
    println("totalProduccion*=2 $totalProduccion")
    totalProduccion /= 4
    println("totalProduccion/=4 $totalProduccion")
    totalProduccion %= 3
    println("totalProduccion%=3 $totalProduccion")

    var ciclos = 0
    ciclos++
    println("ciclos++ $ciclos")
    ciclos--
    println("ciclos-- $ciclos")
}
