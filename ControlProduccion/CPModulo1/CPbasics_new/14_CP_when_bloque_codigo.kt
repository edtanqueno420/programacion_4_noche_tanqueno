fun main() {
    println("When con bloques de codigo - Alertas de Mantenimiento")

    println("Nombre de la maquina:")
    val maquina = readLine()?.trim() ?: ""

    println("Nivel de alerta CRITICO/ALTO/MEDIO/BAJO")
    val nivel = readLine()?.trim()?.uppercase() ?: ""

    when (nivel) {
        "CRITICO" -> {
            println("ALERTA CRITICA - Maquina: $maquina")
            println("Detener produccion inmediatamente")
            println("Notificar a supervisor de planta")
        }
        "ALTO" -> {
            println("ALERTA ALTA - Maquina: $maquina")
            println("Programar mantenimiento urgente")
            println("Reducir velocidad de operacion")
        }
        "MEDIO" ->
            println("Alerta Media - Maquina: $maquina, monitorear temperatura cada hora")

        "BAJO" ->
            println("Alerta Baja - Maquina: $maquina, registrar en bitacora de mantenimiento")

        else ->
            println("Nivel de alerta no reconocido")
    }
    println("Horas de operacion:")
    val horas = readLine()?.toIntOrNull() ?: 0
    val categoria = when (horas) {
        in 0..100 -> "Nueva"
        in 101..500 -> "Rodaje"
        in 501..2000 -> "Operacion normal"
        in 2001..5000 -> "Desgaste moderado"
        else -> "Requiere overhaul"
    }
    println("$horas horas -> $categoria")
}
