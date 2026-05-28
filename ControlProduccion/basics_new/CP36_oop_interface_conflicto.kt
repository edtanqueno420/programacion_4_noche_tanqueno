interface A { fun saludar() = println("Hola desde Modulo A") }
interface B { fun saludar() = println("Hola desde Modulo B") }

class C : A, B {
    override fun saludar() {
        super<A>.saludar()
        super<B>.saludar()
        println("Y desde el Modulo de Control Central")
    }
}

fun main() {
    val modulo = C()
    modulo.saludar()
}
