
fun main() {
    println("Control de flujo")
    println("If Dos Caminos - Aprobacion de lote")
    println("El lote paso la inspeccion de calidad? s/n")
    val pasoInspeccion = readLine()?.trim()?.lowercase()=="s"
    println("Cantidad de piezas en el lote: ")
    val cantidadPiezas = readLine()?.toDoubleOrNull()?:0.0
    if(pasoInspeccion){
        val precioVenta = cantidadPiezas*12.50
        println("Lote aprobado - Valor total: $${precioVenta}")
    }else{
        println("Lote rechazado - $cantidadPiezas piezas a reproceso")
    }
}
