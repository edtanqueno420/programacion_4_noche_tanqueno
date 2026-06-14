fun main() {
    println("When con Condiciones")
    println("Edad del paciente")
    val edad = readLine()?.toIntOrNull()?:0
    println("Tiene seguro s/n")
    val tieneSeguro = readLine()?.trim()?.lowercase()=="s"
    
    val nivelSeguro = if(tieneSeguro){
        println("Nivel del seguro (Basico/Intermedio/Premium")
        readLine()?.trim()?.uppercase()?:""
    }else ""
    val copago = when {
        !tieneSeguro && edad <18 ->0.0
        !tieneSeguro && edad >=65 -> 15.0 
        !tieneSeguro -> 45.0
        nivelSeguro == "Basico" -> 20.0
        nivelSeguro == "Intermedio" -> 10.0
        nivelSeguro == "Premium" -> 0.0
        else -> 30.0
    }
    println("Copago aplicado: $${"%.2f".format(copago)}")
}
   