fun main() {
    println("CICLOS for - Produccion")
    for(i in 1..5){
        println("Lote $i")
    }
    println("UNTIL")
    for(i in 1 until 5){
        println("Estacion $i")
    }

    println("DOWNTO")
    for(i in 5 downTo 1){
        println("Contenedor $i")
    }

    println("Listas")
    val maquinas = listOf("Prensa", "Torno", "Fresadora")
    for(maquina in maquinas){
        println(maquina)
    }

    println("Indice valor")
    for((index, valor)in maquinas.withIndex()){
        println("$index, $valor")
    }

    println("break")
    for (i in 1..5){
        if(i==3){
            break
        }
        println("Pieza $i")
    }

    println("continue")
    for (i in 1..5){
        if(i==3){
            continue
        }
        println("Pieza $i")
    }
}
