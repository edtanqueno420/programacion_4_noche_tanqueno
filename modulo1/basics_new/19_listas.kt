fun main() {
    println("Listas")
    println("Inmutables")
    val frutas=listOf("pera", "manzana", "cereza","Piña", "banana")
    println(frutas)
    println("Size: ${frutas.size}")
    println("Mostrar el elemento indice 0: ${frutas[0]}")
    println("Mostrar primer elemento: ${frutas.first()}")
    println("Mostrar ultimo elemnto: ${frutas.last()}")
    
    println("Mostrar el elemento indice 2: ${frutas.get(2)}")
    println("Mostrar contenido segun indice: ${frutas.indexOf("banana")}")
    println("Verificar existencia de un elemnto: ${frutas.contains("piña")}")
    println("Verificar existencia de un elemneto: ${"naranja" in frutas}")
    
    //Sublistas
    println("Sublista: ${frutas.subList(1,4)}")
    println("Tomar primeros dos elementos: ${frutas.take(2)}")
    println("Suprimir 3 primero elemntos: ${frutas.drop(3)}")
    println("Tomar los ultimos dos elementos: ${frutas.takeLast(2)}")
    println(frutas)
    
    println("Mutables")
    val colores = mutableListOf("blanco", "azul", "amarillo","rojo")
    println(colores)
    colores.add("verde")
    colores.add(0, "morado")
    println(colores)
    colores.remove("verde")
    println(colores)
    colores[1] = "gris"
    println(colores)
    
    println("Array deque")
    val numeros = ArrayDeque<Int>()
    println(numeros)
    numeros.addFirst(1)
    println(numeros)
    numeros.addFirst(3)
    println(numeros)
    numeros.addLast(2)
    println(numeros)
    numeros.removeFirst()
    println(numeros)
    numeros.removeLast()
    println(numeros)
    
}


