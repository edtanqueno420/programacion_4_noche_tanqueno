interface A { fun reportar() = println("Reporte desde modulo A") }
interface B { fun reportar() = println("Reporte desde modulo B") }

class C : A, B {
    override fun reportar() {
        super<A>.reportar()
        super<B>.reportar()
        println("Reporte consolidado desde modulo C")
    }
}
