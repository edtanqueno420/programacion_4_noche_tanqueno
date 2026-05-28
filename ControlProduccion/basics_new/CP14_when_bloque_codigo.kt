fun main() {
    println("When con bloques de codigo - Protocolos de Produccion")

    println("Nombre del operario:")
    val operario = readLine()?.trim() ?: ""

    println("Nivel de alerta CRITICO/ALTO/MEDIO/BAJO")
    val nivel = readLine()?.trim()?.uppercase() ?: ""

    when (nivel) {
        "CRITICO" -> {
            println("ALERTA CRITICA - Operario: $operario")
            println("Detener linea de produccion")
            println("Notificar a supervisor de planta")
        }
        "ALTO" -> {
            println("ALERTA ALTA - Operario: $operario")
            println("Reducir velocidad de produccion")
            println("Evaluar en 10 minutos")
        }
        "MEDIO" ->
            println("MEDIO - Operario: $operario, monitorear y reportar")

        "BAJO" ->
            println("BAJO - Operario: $operario, continuar operacion normal")

        else ->
            println("Nivel no reconocido")
    }
    println("Experiencia del operario (años):")
    val experiencia = readLine()?.toIntOrNull()?:0
    val categoria = when(experiencia){
        in 0..1 -> "Aprendiz"
        in 2..4 -> "Operario Junior"
        in 5..9 -> "Operario Senior"
        in 10..20 -> "Supervisor"
        else -> "Jefe de Planta"

    }
    println("$experiencia años -> $categoria")
}
