
fun main() {
    println("Operadores Logicos en Manufactura")

    val maquinaEncendida = true
    val tieneMantenimiento = false
    val produciendo = true
    val alertaActiva = false

    println("Operador AND &&")
    println("$maquinaEncendida && $tieneMantenimiento ${maquinaEncendida && tieneMantenimiento}")
    println("$maquinaEncendida && $produciendo ${maquinaEncendida && produciendo}")

    println("OR Logico ||")
    println("$maquinaEncendida || $tieneMantenimiento ${maquinaEncendida || tieneMantenimiento}")
    println("$maquinaEncendida || $produciendo ${maquinaEncendida || produciendo}")
    println("$alertaActiva || $tieneMantenimiento ${alertaActiva || tieneMantenimiento}")
    println("$alertaActiva || $tieneMantenimiento || $produciendo ${alertaActiva || tieneMantenimiento || produciendo}")

    println("Not Logico !")
    println("! $maquinaEncendida ${!maquinaEncendida}")
    println("!$alertaActiva ${!alertaActiva}")
    val texto = readLine()
    println(texto)
}
