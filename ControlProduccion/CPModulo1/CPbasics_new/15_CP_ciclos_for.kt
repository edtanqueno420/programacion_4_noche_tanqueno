fun main() {
    println("CICLOS for - Produccion")
    for (i in 1..5) {
        println("Lote $i producido")
    }
    println("UNTIL")
    for (i in 1 until 5) {
        println("Pieza $i")
    }

    println("DOWNTO")
    for (i in 10 downTo 1) {
        println("Cuenta regresiva: $i")
    }

    println("Listas de maquinas")
    val maquinas = listOf("Torno CNC", "Fresadora", "Taladro")
    for (maquina in maquinas) {
        println(maquina)
    }

    println("Indice valor")
    for ((index, valor) in maquinas.withIndex()) {
        println("$index, $valor")
    }

    println("break")
    for (i in 1..5) {
        if (i == 3) {
            break
        }
        println("Lote $i")
    }

    println("continue")
    for (i in 1..5) {
        if (i == 3) {
            continue
        }
        println("Pieza $i")
    }
}
