object ConfiguracionPlanta {
    val nombrePlanta: String = "Manufacturera Los Andes"
    val turnos:  Int    = 3
    private val tokenApi: String = "cp-secreto-456"

    fun baseUrl() = "https://api.$nombrePlanta/produccion"
    fun headers() = mapOf("Authorization" to "Bearer $tokenApi", "Planta" to nombrePlanta)
}

class OperarioPlanta private constructor(val id: Int, val nombre: String) {
    companion object {
        private var contadorId = 0

        fun crear(nombre: String, codigo: String): OperarioPlanta? {
            if (nombre.isBlank() || !codigo.startsWith("OP")) return null
            return OperarioPlanta(++contadorId, nombre.trim())
        }

        const val ROL_DEFECTO = "operario"
    }
}

fun main() {
    println(ConfiguracionPlanta.baseUrl())

    val op = OperarioPlanta.crear("Ana", "OP103")
    println(op)
}
