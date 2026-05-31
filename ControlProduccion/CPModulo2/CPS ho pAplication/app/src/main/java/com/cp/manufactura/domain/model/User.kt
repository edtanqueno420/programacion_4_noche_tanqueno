package com.cp.manufactura.domain.model

data class Operario(
    val id: Int,
    val username: String,
    val email: String,
    val nombre: String,
    val apellido: String,
    val esSupervisor: Boolean,
    val activo: Boolean,
    val fechaIngreso: String,
    val numOrdenes: Int,
)
