
fun main() {
    println("Registro de Produccion")
    println("Ingrese codigo de pieza: ")
    val codigo = readLine()
    println("Pieza registrada: $codigo")

    println("Ingrese cantidad producida: ")
    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
    println("Cantidad registrada: $cantidad")

    val doble = cantidad * 2
    println("El doble de la cantidad es: ${doble}")
    println("El doble de la cantidad es: ${cantidad * 2}")
}
