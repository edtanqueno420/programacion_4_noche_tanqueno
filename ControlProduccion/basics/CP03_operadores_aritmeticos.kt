fun main() {
    val piezasTurno1 = 150
    val piezasTurno2 = 180

    println("=== Control de Produccion ===")
    println("Suma")
    println("Total piezas: ${piezasTurno1 + piezasTurno2}")
    println("Resta")
    println("Diferencia: ${piezasTurno1 - piezasTurno2}")
    println("Multiplicacion")
    println("Produccion semanal (x5): ${piezasTurno1 * 5}")
    println("Division")
    println("Promedio por operario (10 op): ${(piezasTurno1 + piezasTurno2) / 10}")
    println("Modulo")
    println("Piezas restantes: ${150 % 7}")

    println("Operadores de Asignacion Compuestas")
    var inventario = 100
    inventario += 50
    println("inventario+=50 $inventario")
    inventario -= 20
    println("inventario-=20 $inventario")
    inventario *= 2
    println("inventario*=2 $inventario")
    inventario /= 4
    println("inventario/=4 $inventario")
    inventario %= 3
    println("inventario%=3 $inventario")

    var contador = 0
    contador++
    println("contador++ $contador")
    contador--
    println("contador-- $contador")
}
