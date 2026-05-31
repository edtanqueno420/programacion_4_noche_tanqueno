package com.cp.manufactura.model

data class Maquina(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val ubicacion: String,
    val operativa: Boolean = true
)

val maquinasDeMuestra = listOf(
    Maquina(1, "CNC Fresadora", "Fresado", "Nave A - Sector 1", operativa = true),
    Maquina(2, "Torno Automático", "Tornería", "Nave A - Sector 2", operativa = true),
    Maquina(3, "Prensa Hidráulica", "Conformado", "Nave B - Sector 1", operativa = false),
    Maquina(4, "Robot Soldador", "Soldadura", "Nave B - Sector 2", operativa = true),
    Maquina(5, "Cinta Transportadora", "Transporte", "Nave C - Sector 1", operativa = true),
    Maquina(6, "Taladro Industrial", "Perforado", "Nave A - Sector 3", operativa = true),
    Maquina(7, "Cortadora Láser", "Corte", "Nave B - Sector 3", operativa = false),
    Maquina(8, "Impresora 3D", "Aditiva", "Nave C - Sector 2", operativa = true),
)
