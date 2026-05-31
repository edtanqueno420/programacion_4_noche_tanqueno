package com.cp.manufactura.domain.model

data class AccessTokens(
    val access: String,
    val refresh: String,
)

data class LoggedOperator(
    val id: Int,
    val username: String,
    val nombre: String,
    val esSupervisor: Boolean,
)
