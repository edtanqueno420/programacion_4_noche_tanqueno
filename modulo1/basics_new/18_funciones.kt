fun main() {
    saludar()
    saludarConParametros("Tjota")
    val numero1=10
    val numero2=20
    println("Suma de $numero1 + $numero2 = ${sumar(numero1, numero2)}")
    println("Resta de $numero1 - $numero2 = ${restar(numero1, numero2)}")
    println("Multiplicar $numero1 * $numero2 = ${multiplicar(numero1, numero2)}")
    
}

fun saludar(){
    println("Hello world with function")
}

fun saludarConParametros(nombre:String){
    println("Buenas noches : $nombre")
}

fun sumar(numero1: Int, numero2:Int): Int{
    return numero1+numero2
}

//Funcion simplificada
fun restar(numero1: Int, numero2:Int) = numero1-numero2

//Funcion dentro de funcion
fun operaciones(){
    fun cuadrado(x: Int)=x*x
    println(cuadrado(5))
}

//Funciones como variables
val multiplicar = {a: Int, b: Int-> a*b}

