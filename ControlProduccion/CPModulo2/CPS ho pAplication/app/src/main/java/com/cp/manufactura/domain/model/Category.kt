package com.cp.manufactura.domain.model

data class LineaProduccion(
    val id: Int,
    val nombre: String,
    val slug: String,
    val descripcion: String,
    val activa: Boolean,
    val totalMaquinas: Int,
)
