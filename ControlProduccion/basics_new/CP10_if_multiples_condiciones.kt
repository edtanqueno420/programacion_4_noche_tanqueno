
fun main(){
    println("If con multiples condiciones - Eficiencia")
    println("Indice de eficiencia de produccion: ")
    val eficiencia = readLine()?.toIntOrNull()?:0
    val clasificacion = if(eficiencia < 50){
        "Critico"
    }else if (eficiencia <= 69){
        "Bajo"
    }else if (eficiencia <= 84){
        "Estandar"
    }else if (eficiencia <= 95){
        "Alto"
    }else{
        "Excelente"
    }
    println("Clasificacion: $clasificacion")
}
