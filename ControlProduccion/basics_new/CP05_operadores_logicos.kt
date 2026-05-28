
fun main() {
    println("Operadores Logicos - Control Produccion")

    val maquinaOperativa = true
    val hayMateriaPrima = false
    val personalAsignado = true
    val mantenimientoPendiente = false

    println("Operador AND &&")
    println("$maquinaOperativa && $hayMateriaPrima ${maquinaOperativa && hayMateriaPrima}")
    println("$maquinaOperativa && $personalAsignado ${maquinaOperativa && personalAsignado}")

    println("OR Logico ||")
    println("$maquinaOperativa || $hayMateriaPrima ${maquinaOperativa || hayMateriaPrima}")
    println("$maquinaOperativa || $personalAsignado ${maquinaOperativa || personalAsignado}")
    println("$mantenimientoPendiente || $hayMateriaPrima ${mantenimientoPendiente || hayMateriaPrima}")
    println("$mantenimientoPendiente || $hayMateriaPrima || $personalAsignado ${mantenimientoPendiente || hayMateriaPrima || personalAsignado}")

    println("Not Logico !")
    println("! $maquinaOperativa ${!maquinaOperativa}")
    println("!$mantenimientoPendiente ${!mantenimientoPendiente} ")


    println("Ingrese codigo de produccion:")
    val codigo = readLine()
    println(codigo)
}
