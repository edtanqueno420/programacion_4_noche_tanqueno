object ConfiguracionPlanta {
    val host: String = "produccion.planta.local"
    val puerto: Int = 8080
    private val apiKey: String = "cp-secreto-456"

    fun baseUrl() = "http://$host:$puerto"
    fun headers() = mapOf("Authorization" to "Bearer $apiKey")
}

class PiezaCatalogada private constructor(val id: Int, val nombre: String) {
    companion object {
        private var contadorId = 0

        fun crear(nombre: String, codigo: String): PiezaCatalogada? {
            if (nombre.isBlank() || codigo.isBlank()) return null
            return PiezaCatalogada(++contadorId, nombre.trim())
        }

        const val CATEGORIA_DEFECTO = "Estandar"
    }
}

fun main() {
    println(ConfiguracionPlanta.baseUrl())

    val p = PiezaCatalogada.crear("Eje principal", "EP-001")
    println(p)
}
