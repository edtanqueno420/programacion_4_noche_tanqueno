abstract class Equipo(val nombre: String) {
    abstract val capacidad: Double
    abstract val consumoEnergetico: Double
    abstract fun descripcion(): String

    fun comparar(otro: Equipo): String = when {
        capacidad > otro.capacidad -> "$nombre tiene mayor capacidad que ${otro.nombre}"
        capacidad < otro.capacidad -> "$nombre tiene menor capacidad que ${otro.nombre}"
        else             -> "$nombre y ${otro.nombre} tienen la misma capacidad"
    }

    override fun toString() = "${descripcion()} | Capacidad: ${"%.2f".format(capacidad)}"
}

class HornoIndustrial(val temperaturaMax: Double) : Equipo("Horno Industrial") {
    override val capacidad:       Double get() = temperaturaMax
    override val consumoEnergetico:  Double get() = temperaturaMax * 0.5
    override fun descripcion() = "Horno industrial con temp max $temperaturaMax °C"
}

class Compresor(val presionMax: Double, val caudal: Double) : Equipo("Compresor") {
    override val capacidad:       Double get() = presionMax * caudal
    override val consumoEnergetico:  Double get() = presionMax * 1.2
    override fun descripcion() = "Compresor de ${presionMax} PSI y ${caudal} L/min"
}

class Generador(val potenciaKw: Double) : Equipo("Generador") {
    override val capacidad:       Double get() = potenciaKw * 1000
    override val consumoEnergetico:  Double get() = potenciaKw * 0.8
    override fun descripcion() = "Generador de $potenciaKw kW"
}

fun main() {
    val equipos: List<Equipo> = listOf(
        HornoIndustrial(1200.0),
        Compresor(150.0, 500.0),
        Generador(250.0)
    )

    equipos.forEach { println(it) }

    val mayor = equipos.maxByOrNull { it.capacidad }
    println("\nEquipo con mayor capacidad: ${mayor?.nombre}")

    println(equipos[0].comparar(equipos[1]))
}
