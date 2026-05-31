package com.cp.manufactura.model

data class Pieza(
    val id: Int,
    val nombre: String,
    val codigo: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val activo: Boolean = true
)

data class PiezaApi(
    val id: Int,
    val name: String,
    val slug: String,
    val price: String,
    val stock: Int,
    val is_active: Boolean,
    val url_image: String,
    val category_name: String
)

data class PaginatedResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PiezaApi>
)

val piezasDeMuestra = listOf(
    Pieza(1, "Engranaje recto 12mm", "EN-001", 89.99, "Engranajes", 120),
    Pieza(2, "Eje de transmisión 40cm", "ET-002", 349.99, "Ejes", 45),
    Pieza(3, "Tornillo M8x30", "TN-003", 29.99, "Fijación", 500, activo = false),
    Pieza(4, "Rodamiento 6205", "RD-004", 149.99, "Rodamientos", 230),
    Pieza(5, "Resorte de compresión", "RS-005", 59.99, "Resortes", 180),
    Pieza(6, "Junta tórica 20mm", "JT-006", 39.99, "Sellado", 450),
    Pieza(7, "Leva excéntrica", "LV-007", 89.99, "Mecanizado", 90),
    Pieza(8, "Soporte angular L", "SP-008", 24.99, "Estructura", 300),
)
