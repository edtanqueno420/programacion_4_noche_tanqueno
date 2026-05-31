open class Maquina(val nombre: String, val sonido: String) {
    open fun hacerSonido() = println("$nombre suena: $sonido")
    open fun descripcion() = "Soy $nombre"

    fun detener() = println("$nombre detenida")
}

class Torno(nombre: String) : Maquina(nombre, "Zzzzrrr") {
    override fun hacerSonido() {
        super.hacerSonido()
        println("(vibracion de corte detectada)")
    }
    override fun descripcion() = "${super.descripcion()}, un torno CNC"
}

class Fresadora(nombre: String, val cincoEjes: Boolean) : Maquina(nombre, "Brrrrrm") {
    override fun descripcion() =
        "${super.descripcion()}, una fresadora ${if (cincoEjes) "de 5 ejes" else "de 3 ejes"}"
}

fun main() {
    val torno = Torno("Torno Haas ST-10")
    torno.hacerSonido()

    val fresadora = Fresadora("Fresadora DMG", true)
    println(fresadora.descripcion())

    torno.detener()
}
