fun main() {
    println("Prioridad de pedidos en produccion")

    println("Es pedido urgente? (s/n)")
    val urgente = readLine()?.trim()?.lowercase() == "s"

    println("Requiere materia prima importada? (s/n)")
    val materialImportado = readLine()?.trim()?.lowercase() == "s"

    println("Cantidad de unidades solicitadas:")
    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0

    val prioridad = when {
        urgente && materialImportado -> "URGENTE MAXIMA"
        urgente -> "URGENTE"
        cantidad >= 1000 -> "ALTA PRIORIDAD"
        cantidad >= 500 -> "PRIORIDAD MEDIA"
        else -> "PRIORIDAD NORMAL"
    }

    println("Resultado: $prioridad")
}
