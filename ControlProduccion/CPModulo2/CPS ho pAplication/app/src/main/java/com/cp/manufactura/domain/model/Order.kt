package com.cp.manufactura.domain.model

enum class OrderStatus {
    PENDIENTE,
    CONFIRMADA,
    EN_PROCESO,
    COMPLETADA,
    CANCELADA;

    companion object {
        fun fromString(value: String): OrderStatus {
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: PENDIENTE
        }
    }
}

data class OrdenProduccion(
    val id: Int,
    val codigoOrden: String,
    val status: OrderStatus,
    val operarioId: Int,
    val piezaId: Int,
    val cantidad: Int,
    val fechaCreacion: String,
    val fechaCompletado: String?,
)
