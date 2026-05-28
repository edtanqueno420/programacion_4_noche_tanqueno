
fun main() {
    println("Registro de Produccion")
    println("Ingrese nombre del operario: ")
    val nombre = readLine()
    println("Operario: $nombre")

    println("Ingrese cantidad de piezas producidas: ")
    val piezas = readLine()?.toDoubleOrNull()?:0.00
    println("Piezas registradas: $piezas")

    val doble = piezas*2
    println("El doble de piezas es: ${doble}")
    println("El doble de piezas es: ${piezas*2}")

}
