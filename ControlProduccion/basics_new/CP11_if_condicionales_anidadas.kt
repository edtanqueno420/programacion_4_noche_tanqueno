fun main() {
    println("If con condiciones anidadas - Diagnostico de Maquina")
    println("La maquina tiene historial de fallos? s/n")
    val tieneHistorial = readLine()?.trim()?.lowercase()=="s"
    println("Temperatura del motor (°C): ")
    val temperatura = readLine()?.toIntOrNull()?:0
    if (tieneHistorial) {
        println("Maquina con historial de fallos")
        if(temperatura<80){
            println("Temperatura normal - Operativa")

        }else if(temperatura>110){
            println("Sobrecalentamiento critico")
        }else{
            println("Temperatura elevada - Monitorear")
        }
    }else{
       println("Maquina sin historial de fallos")
       if (temperatura<60 || temperatura>100){
           println("Temperatura fuera del rango optimo")
       }else{
           println("Temperatura optima de operacion")
       }
   }
}
