interface A {
    fun iniciar() = println("Proceso A - Corte inicializado")
}
interface B {
    fun iniciar() = println("Proceso B - Ensamble inicializado")
}

class C : A, B {
    override fun iniciar() {
        super<A>.iniciar()
        super<B>.iniciar()
        println("Proceso C - Control de calidad finalizado")
    }
}

fun main() {
    val proceso = C()
    proceso.iniciar()
}
