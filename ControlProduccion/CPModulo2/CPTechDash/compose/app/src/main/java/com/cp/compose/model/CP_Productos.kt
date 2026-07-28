package com.cp.compose.model

data class CP_Producto(
    val id:        Int,
    val nombre:    String,
    val precio:    Double,
    val categoria: String,
    val stock:     Int,
    val activo:    Boolean = true
)

data class CP_ProductoApi(
    val id:            Int,
    val name:          String,
    val slug:          String,
    val price:         String,
    val stock:         Int,
    val is_active:     Boolean,
    val url_image:     String,
    val category_name: String
)

data class CP_PaginatedResponse(
    val count:    Int,
    val next:     String?,
    val previous: String?,
    val results:  List<CP_ProductoApi>
)

val CP_productosDeMuestra = listOf(
    CP_Producto(1, "Tornillo M8",        0.15,  "Herramientas", 5000),
    CP_Producto(2, "Motor Electrico 5HP", 299.99, "Motores",   8),
    CP_Producto(3, "Cinta Transportadora", 149.99, "Transporte", 3, activo = false),
    CP_Producto(4, "Sensor de Temperatura", 45.99, "Sensores",  23),
    CP_Producto(5, "Valvula Solenoide",    89.99,  "Valvulas",     11),
    CP_Producto(6, "Filtro Hidraulico",    34.99,  "Filtros",  30),
    CP_Producto(7, "Rodamiento 6205",      12.99,  "Rodamientos", 19),
    CP_Producto(8, "Correa V",             8.99,   "Correas", 55),
)
