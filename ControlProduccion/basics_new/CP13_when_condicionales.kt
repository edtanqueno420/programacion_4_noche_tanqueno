fun main() {
    println("When con Condiciones - Costo de Produccion")
    println("Cantidad de piezas a fabricar:")
    val cantidad = readLine()?.toIntOrNull()?:0
    println("Es produccion interna? s/n")
    val interna = readLine()?.trim()?.lowercase()=="s"

    val tipoMaterial = if(interna){
        println("Tipo material (Plastico/Metal/Madera)")
        readLine()?.trim()?.uppercase()?:""
    }else ""
    val costoUnitario = when {
        !interna && cantidad <100 -> 25.50
        !interna && cantidad >=500 -> 12.00
        !interna -> 18.75
        tipoMaterial == "Plastico" -> 3.50
        tipoMaterial == "Metal" -> 8.20
        tipoMaterial == "Madera" -> 6.10
        else -> 5.00
    }
    println("Costo unitario: $${"%.2f".format(costoUnitario)}")
    println("Costo total: $${"%.2f".format(costoUnitario*cantidad)}")
}
