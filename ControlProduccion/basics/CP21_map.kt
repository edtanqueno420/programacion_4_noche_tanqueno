fun main() {
    println("=== Map - Registro de Maquinas ===")
    println("Inmutables")
    val maquinas = mapOf(
        "Torno-01" to "Operativo",
        "Fresa-02" to "Mantenimiento",
        "Prensa-03" to "Operativo",
        "Robot-04" to "Detenido"
    )
    println(maquinas["Torno-01"])
    println(maquinas["Torno-05"])
    println(maquinas.getOrDefault("Torno-01", "Desconocido"))
    println(maquinas.getOrDefault("Torno-05", "Desconocido"))
    println(maquinas.keys)
    println(maquinas.values)
    println(maquinas.entries)
    for ((maquina, estado) in maquinas) {
        println("Maquina: $maquina - Estado: $estado")
    }
    for (entrada in maquinas) {
        println("Entrada: $entrada")
    }

    println("Mutables")
    val produccionDiaria = mutableMapOf(
        "Lunes" to 350,
        "Martes" to 420,
        "Miercoles" to 380,
        "Jueves" to 410
    )
    produccionDiaria["Viernes"] = 450
    println(produccionDiaria)
    produccionDiaria["Martes"] = 440
    println(produccionDiaria)
    produccionDiaria.remove("Miercoles")
    println(produccionDiaria)
    produccionDiaria.getOrPut("Sabado") { 300 }
    println(produccionDiaria)
    produccionDiaria.getOrPut("Lunes") { 500 }
    println(produccionDiaria)
}
