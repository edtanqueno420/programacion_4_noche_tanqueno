fun main() {
    println("Ciclos while - Linea de Ensamblaje")
    println("while basico")
    var contador=1
    while (contador<=5){
        println("Ensamblando unidad $contador")
        contador++
    }

    contador = 1
    do{
        println("Empacando caja $contador")
        contador++
    }while(contador<=5)

    println("break continue")
    contador=1
    while (contador<=10){
        contador++
        if(contador ==3) continue
        if(contador==7)break
        println("Procesando lote $contador")
    }

    var input: String
    while(true){
        println("Escribe 'salir' para detener produccion:")
        input=readLine()?:""
        if(input=="salir")break
        println("Produciendo: $input")
    }


}
