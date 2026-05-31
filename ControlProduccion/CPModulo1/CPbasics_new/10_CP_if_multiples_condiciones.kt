fun main() {
    println("Clasificacion de piezas por defectos")
    println("Porcentaje de defectos detectados en el lote:")
    val defectos = readLine()?.toIntOrNull() ?: 0
    val clasificacion = if (defectos < 1) {
        "Clase A - Calidad Premium"
    } else if (defectos <= 3) {
        "Clase B - Estandar"
    } else if (defectos <= 7) {
        "Clase C - Aceptable con observaciones"
    } else if (defectos <= 15) {
        "Clase D - Reproceso requerido"
    } else {
        "Clase F - Lote rechazado"
    }
    println("Clasificacion: $clasificacion")
}
