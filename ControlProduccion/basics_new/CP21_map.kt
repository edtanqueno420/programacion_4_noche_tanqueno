fun main() {
    println("Map - Inventario")
    println("Inmutables")
    val almacenes = mapOf(
        "Acero" to "Almacen Norte",
        "Aluminio" to "Almacen Sur",
        "Plastico" to "Almacen Este",
        "Madera" to "Almacen Oeste"
    )
    println(almacenes["Acero"])
    println(almacenes["Caucho"])
    println(almacenes.getOrDefault("Acero", "Desconocido"))
    println(almacenes.getOrDefault("Caucho", "Desconocido"))
    println(almacenes.keys)
    println(almacenes.values)
    println(almacenes.entries)
    println(almacenes)
    for((material, almacen) in almacenes){
        println("material: $material - almacen: $almacen")
    }
    for(almacen in almacenes){
        println("almacen: $almacen")
    }

    println("Mutables")
    val inventario = mutableMapOf(
        "Tornillos" to 5000,
        "Tuercas" to 3200,
        "Arandelas" to 2800,
        "Clavos" to 1500
    )
    inventario["Pernos"] = 2000
    println(inventario)
    inventario["Tornillos"] = 7500
    println(inventario)
    inventario.remove("Clavos")
    println(inventario)
    inventario.getOrPut("Remaches"){3000}
    println(inventario)
    inventario.getOrPut("Tuercas"){1000}
    println(inventario)
}
