open class Vehiculo(
    val marca: String,
    val modelo: String,
    val anio: Int
) {
    open fun mostrarDatos() {
        println("Marca: $marca")
        println("Modelo: $modelo")
        println("Anio: $anio")
    }
}

class Auto(
    marca: String,
    modelo: String,
    anio: Int,
    val numPuertas: Int
) : Vehiculo(marca, modelo, anio) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Numero de puertas: $numPuertas")
    }
}

class Motocicleta(
    marca: String,
    modelo: String,
    anio: Int,
    val cilindrada: Int
) : Vehiculo(marca, modelo, anio) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Cilindrada: $cilindrada cc")
    }
}

fun main() {
    val auto1 = Auto("Toyota", "Corolla", 2022, 4)
    val auto2 = Auto("Ford", "Mustang", 2020, 2)
    val moto1 = Motocicleta("Yamaha", "MT-07", 2023, 689)
    val moto2 = Motocicleta("Honda", "CBR 600RR", 2021, 599)

    println("--- Auto 1 ---")
    auto1.mostrarDatos()
    println()
    println("--- Auto 2 ---")
    auto2.mostrarDatos()
    println()
    println("--- Motocicleta 1 ---")
    moto1.mostrarDatos()
    println()
    println("--- Motocicleta 2 ---")
    moto2.mostrarDatos()
}
