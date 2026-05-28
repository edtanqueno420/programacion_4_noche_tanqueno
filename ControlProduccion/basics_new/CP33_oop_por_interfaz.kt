interface Procesable {
    fun procesar(unidades: Int): Boolean
    val nombre: String
}

class TornoCNC(val codigo: String) : Procesable {
    override val nombre = "Torno CNC"
    override fun procesar(unidades: Int): Boolean {
        println("Mecanizando $unidades piezas en $codigo")
        return true
    }
}

class Impresora3D(val modelo: String) : Procesable {
    override val nombre = "Impresora 3D"
    override fun procesar(unidades: Int): Boolean {
        println("Imprimiendo $unidades piezas en $modelo")
        return true
    }
}

class Ensamblaje : Procesable {
    override val nombre = "Ensamblaje Manual"
    override fun procesar(unidades: Int): Boolean {
        println("Ensamblando $unidades unidades manualmente")
        return true
    }
}

class RobotSoldador : Procesable {
    override val nombre = "Robot Soldador"
    override fun procesar(unidades: Int): Boolean {
        println("Soldando $unidades uniones con robot")
        return true
    }
}

fun fabricar(unidades: Int, proceso: Procesable) {
    println("Iniciando fabricacion con ${proceso.nombre}...")
    val exito = proceso.procesar(unidades)
    println(if (exito) "Fabricacion exitosa" else "Fabricacion fallida")
}

fun main() {
    val procesos: List<Procesable> = listOf(
        TornoCNC("TC-2024"),
        Impresora3D("MK4-Pro"),
        Ensamblaje(),
        RobotSoldador()
    )

    procesos.forEach { fabricar(50, it) }
}
