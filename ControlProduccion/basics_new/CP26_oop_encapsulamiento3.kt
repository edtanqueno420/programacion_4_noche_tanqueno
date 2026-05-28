class Operario(val nombre: String, val codigo: String) {
    val nombreNormalizado: String
    val turnoAsignado: String

    init {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacio" }
        require(codigo.startsWith("OP")) { "Codigo invalido: $codigo" }

        nombreNormalizado = nombre.trim().lowercase()
        turnoAsignado      = when (codigo.substringAfter("OP").firstOrNull()) {
            '1' -> "Manana"
            '2' -> "Tarde"
            '3' -> "Noche"
            else -> "Rotativo"
        }
    }
}

fun main() {
    val op = Operario("  Carlos Lopez  ", "OP102")
    println(op.nombreNormalizado)
    println(op.turnoAsignado)
}
