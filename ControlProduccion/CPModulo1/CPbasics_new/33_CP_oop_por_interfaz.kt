interface Procesable {
    fun ejecutar(cantidad: Int): Boolean
    val nombre: String
}

class CorteCNC(val programa: String) : Procesable {
    override val nombre = "Corte CNC"
    override fun ejecutar(cantidad: Int): Boolean {
        println("Cortando $cantidad piezas con programa $programa")
        return true
    }
}

class SoldaduraRobotizada(val celda: String) : Procesable {
    override val nombre = "Soldadura Robotizada"
    override fun ejecutar(cantidad: Int): Boolean {
        println("Soldando $cantidad uniones en celda $celda")
        return true
    }
}

class InspeccionVisual : Procesable {
    override val nombre = "Inspeccion Visual"
    override fun ejecutar(cantidad: Int): Boolean {
        println("Inspeccionando $cantidad piezas visualmente")
        return true
    }
}

class Pintura : Procesable {
    override val nombre = "Pintura"
    override fun ejecutar(cantidad: Int): Boolean {
        println("Pintando $cantidad piezas en cabina")
        return true
    }
}

fun procesar(cantidad: Int, proceso: Procesable) {
    println("Iniciando ${proceso.nombre}...")
    val exito = proceso.ejecutar(cantidad)
    println(if (exito) "OK Proceso exitoso" else "ERROR Proceso fallido")
}

fun main() {
    val procesos: List<Procesable> = listOf(
        CorteCNC("NC-001"),
        SoldaduraRobotizada("Celda-3"),
        InspeccionVisual(),
        Pintura()
    )

    procesos.forEach { procesar(100, it) }
}
