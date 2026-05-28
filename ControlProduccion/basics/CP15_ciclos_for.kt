fun main() {
    println("=== CICLOS for - Control de Lotes ===")
    for (i in 1..5) {
        println("Lote $i procesado")
    }
    println("UNTIL")
    for (i in 1 until 5) {
        println("Estacion $i")
    }

    println("DOWNTO")
    for (i in 5 downTo 1) {
        println("Cuenta regresiva: $i")
    }

    println("Listas")
    val maquinas = listOf("Torno", "Fresa", "Prensa")
    for (maquina in maquinas) {
        println(maquina)
    }

    println("Indice valor")
    for ((index, valor) in maquinas.withIndex()) {
        println("$index, $valor")
    }

    println("break")
    for (i in 1..5) {
        if (i == 3) break
        println(i)
    }

    println("continue")
    for (i in 1..5) {
        if (i == 3) continue
        println(i)
    }
}
