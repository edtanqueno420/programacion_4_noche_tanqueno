fun main() {
    println("Map - Catalogo de Piezas")
    println("Inmutables")
    val materiales = mapOf(
        "PZ-001" to "Acero",
        "PZ-002" to "Aluminio",
        "PZ-003" to "Cobre",
        "PZ-004" to "Titanio"
    )
    println(materiales["PZ-001"])
    println(materiales["PZ-999"])
    println(materiales.getOrDefault("PZ-001", "Desconocido"))
    println(materiales.getOrDefault("PZ-999", "Desconocido"))
    println(materiales.keys)
    println(materiales.values)
    println(materiales.entries)
    println(materiales)
    for ((codigo, material) in materiales) {
        println("codigo: $codigo - material: $material")
    }
    for (material in materiales) {
        println("material: $material")
    }

    println("Mutables")
    val stock = mutableMapOf(
        "Eje" to 50,
        "Buje" to 120,
        "Engranaje" to 30,
        "Tornillo" to 500
    )
    stock["Tuerca"] = 400
    println(stock)
    stock["Eje"] = 45
    println(stock)
    stock.remove("Tornillo")
    println(stock)
    stock.getOrPut("Arandela") { 200 }
    println(stock)
    stock.getOrPut("Engranaje") { 200 }
    println(stock)
}
