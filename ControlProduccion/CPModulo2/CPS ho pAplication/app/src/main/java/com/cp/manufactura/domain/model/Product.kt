package com.cp.manufactura.domain.model

data class Pieza(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val precioConCosto: Double,
    val stock: Int,
    val enExistencia: Boolean,
    val activo: Boolean,
    val codigoPieza: String,
    val lineaId: Int,
    val lineaNombre: String,
)
