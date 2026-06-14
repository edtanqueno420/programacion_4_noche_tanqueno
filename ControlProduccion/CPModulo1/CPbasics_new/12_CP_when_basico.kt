fun main() {
    println("Prioridad de ordenes de produccion")

    println("El pedido es urgente? (s/n)")
    val urgente = readLine()?.trim()?.lowercase() == "s"

    println("Hay material disponible en stock? (s/n)")
    val materialDisponible = readLine()?.trim()?.lowercase() == "s"

    println("Ingrese la cantidad solicitada:")
    val cantidad = readLine()?.toIntOrNull() ?: 0

    val prioridad = when {
        urgente && materialDisponible -> "CRITICA - Producir inmediatamente"
        urgente -> "ALTA - Programar con prioridad"
        cantidad >= 1000 -> "MEDIA - Lote grande"
        cantidad >= 500 -> "MEDIA-BAJA - Lote mediano"
        else -> "BAJA - Produccion normal"
    }

    println("Resultado: $prioridad")
}
