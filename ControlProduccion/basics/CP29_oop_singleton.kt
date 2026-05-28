object ConfiguracionPlanta {
    val nombrePlanta: String = "Planta Central"
    val turnos: Int = 3
    private val claveAcceso: String = "prod-secreto-456"

    fun baseUrl() = "https://$nombrePlanta/produccion"
    fun headers() = mapOf("Authorization" to "Bearer $claveAcceso")
}

class Operario private constructor(val id: Int, val nombre: String) {
    companion object {
        private var contadorId = 0

        fun crear(nombre: String, turno: String): Operario? {
            if (nombre.isBlank() || turno.isBlank()) return null
            return Operario(++contadorId, nombre.trim())
        }

        const val TURNO_DEFECTO = "Matutino"
    }
}

fun main() {
    println(ConfiguracionPlanta.baseUrl())

    val op = Operario.crear("Juan Perez", "Matutino")
    println(op)
}
