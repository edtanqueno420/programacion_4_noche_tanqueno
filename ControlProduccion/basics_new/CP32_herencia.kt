open class Maquina1(val nombre: String)
// class Torno : Maquina1("Torno CNC")  // ERROR — Maquina1 es final

open class Maquina(val nombre: String, val sonido: String) {
    open fun hacerSonido() = println("$nombre hace: $sonido")
    open fun descripcion() = "Soy $nombre"

    fun encender() = println("$nombre encendida")
}

class Torno(nombre: String) : Maquina(nombre, "Zzzzz") {
    override fun hacerSonido() {
        super.hacerSonido()
        println("(cortando metal)")
    }
    override fun descripcion() = "${super.descripcion()}, un torno CNC"
}

class Prensa(nombre: String, val automatica: Boolean) : Maquina(nombre, "Pum") {
    override fun descripcion() =
        "${super.descripcion()}, una prensa ${if (automatica) "automatica" else "manual"}"
}

fun main() {
    val torno = Torno("Torno CNC-3000")
    torno.hacerSonido()

    val prensa = Prensa("Prensa H-200", true)
    println(prensa.descripcion())

    torno.encender()
}
