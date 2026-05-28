fun main() {
    println("=== Registro de Produccion ===")
    println("Nombre del operador: ")
    val nombre = readLine()
    println("Operador: $nombre")

    println("Cantidad de piezas producidas: ")
    val piezas = readLine()?.toDoubleOrNull() ?: 0.0
    println("Piezas registradas: $piezas")

    val doble = piezas * 2
    println("El doble de produccion es: $doble")
}
