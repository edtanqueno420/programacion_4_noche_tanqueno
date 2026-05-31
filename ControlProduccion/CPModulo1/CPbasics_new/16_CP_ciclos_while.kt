fun main() {
    println("Ciclos while - Monitoreo de Linea")
    println("while basico")
    var contador = 1
    while (contador <= 5) {
        println("Inspeccion de estacion $contador")
        contador++
    }

    contador = 1
    do {
        println("Ciclo de produccion $contador")
        contador++
    } while (contador <= 5)

    println("break continue")
    contador = 1
    while (contador <= 10) {
        contador++
        if (contador == 3) continue
        if (contador == 7) break
        println("Unidad producida: $contador")
    }

    var input: String
    while (true) {
        println("Escribe 'salir' para detener la linea:")
        input = readLine() ?: ""
        if (input == "salir") break
        println("Registraste: $input")
    }
}
