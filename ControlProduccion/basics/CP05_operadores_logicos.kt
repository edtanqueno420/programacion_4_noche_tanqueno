fun main() {
    println("=== Control de Calidad - Operadores Logicos ===")
    val pasaDimension = true
    val pasaPeso = false
    val pasaAcabado = true
    val rechazado = false

    println("AND &&")
    println("$pasaDimension && $pasaPeso ${pasaDimension && pasaPeso}")
    println("$pasaDimension && $pasaAcabado ${pasaDimension && pasaAcabado}")

    println("OR ||")
    println("$pasaDimension || $pasaPeso ${pasaDimension || pasaPeso}")
    println("$pasaDimension || $pasaAcabado ${pasaDimension || pasaAcabado}")
    println("$rechazado || $pasaPeso ${rechazado || pasaPeso}")

    println("NOT !")
    println("!$pasaDimension ${!pasaDimension}")
    println("!$rechazado ${!rechazado}")
}
