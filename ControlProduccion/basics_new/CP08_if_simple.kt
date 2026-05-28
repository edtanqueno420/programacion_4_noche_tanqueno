
fun main() {
    println("Control de calidad")
    println("If Simple")
    println("Porcentaje de defectos en el lote:")
    val porcentajeDefectos = readLine()?.toDoubleOrNull()?:0.0
    if(porcentajeDefectos>=5){
        println("Lote rechazado por control de calidad")
    }
    if(porcentajeDefectos>=10){
        println("Lote con defectos criticos")
    }
    println("Porcentaje registrado: $porcentajeDefectos%")
}
