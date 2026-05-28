fun main() {
    saludar()
    saludarConParametros("Tjota")
    val numero1=150
    val numero2=80
    println("Produccion total = ${sumar(numero1, numero2)}")
    println("Diferencia = ${restar(numero1, numero2)}")
    println("Multiplicar $numero1 * $numero2 = ${multiplicar(numero1, numero2)}")

}

fun saludar(){
    println("Sistema de Produccion activo")
}

fun saludarConParametros(nombre:String){
    println("Bienvenido operario: $nombre")
}

fun sumar(numero1: Int, numero2:Int): Int{
    return numero1+numero2
}

fun restar(numero1: Int, numero2:Int) = numero1-numero2

fun operaciones(){
    fun cuadrado(x: Int)=x*x
    println(cuadrado(5))
}

val multiplicar = {a: Int, b: Int-> a*b}
