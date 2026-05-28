open class MaquinaBase(val nombre: String, val sonido: String) {
    open fun hacerSonido() = println("$nombre hace: $sonido")
    open fun descripcion() = "Soy $nombre"
    fun encender() = println("$nombre encendida")
}

class Torno(nombre: String) : MaquinaBase(nombre, "Zzzz") {
    override fun hacerSonido() {
        super.hacerSonido()
        println("(cortando metal)")
    }
    override fun descripcion() = "${super.descripcion()}, un torno"
}

class Prensa(nombre: String, val automatizada: Boolean) : MaquinaBase(nombre, "Pum") {
    override fun descripcion() =
        "${super.descripcion()}, prensa ${if (automatizada) "automatizada" else "manual"}"
}

fun main() {
    val torno = Torno("Torno CNC-3000")
    torno.hacerSonido()

    val prensa = Prensa("Prensa H-2000", true)
    println(prensa.descripcion())

    torno.encender()
}
