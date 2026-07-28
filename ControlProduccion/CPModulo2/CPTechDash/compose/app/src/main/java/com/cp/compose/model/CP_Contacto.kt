package com.cp.compose.model

data class CP_Contacto(
    val id:       Int,
    val nombre:   String,
    val email:    String,
    val telefono: String,
    val favorito: Boolean = false
)

val CP_contactosDeMuestra = listOf(
    CP_Contacto(1, "Ana Garcia",    "ana@ejemplo.com",    "+593 99 111 2222", favorito = true),
    CP_Contacto(2, "Luis Martinez", "luis@ejemplo.com",   "+593 99 333 4444"),
    CP_Contacto(3, "Maria Lopez",   "maria@ejemplo.com",  "+593 99 555 6666", favorito = true),
    CP_Contacto(4, "Carlos Ruiz",   "carlos@ejemplo.com", "+593 99 777 8888"),
    CP_Contacto(5, "Sofia Torres",  "sofia@ejemplo.com",  "+593 99 999 0000"),
    CP_Contacto(6, "Pedro Mora",    "pedro@ejemplo.com",  "+593 98 111 2222"),
    CP_Contacto(7, "Elena Vega",    "elena@ejemplo.com",  "+593 98 333 4444", favorito = true),
    CP_Contacto(8, "Diego Paz",     "diego@ejemplo.com",  "+593 98 555 6666"),
)
