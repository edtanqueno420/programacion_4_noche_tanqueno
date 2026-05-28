fun main() {
    println("=== Ciclos while - Linea de Ensamble ===")
    var contador = 1
    while (contador <= 5) {
        println("Pieza $contador ensamblada")
        contador++
    }

    contador = 1
    do {
        println("Inspeccion $contador")
        contador++
    } while (contador <= 5)

    println("break continue")
    contador = 1
    while (contador <= 10) {
        contador++
        if (contador == 3) continue
        if (contador == 7) break
        println(contador)
    }

    var input: String
    while (true) {
        println("Escribe 'salir' para terminar:")
        input = readLine() ?: ""
        if (input == "salir") break
        println("Ingresaste $input")
    }
}
