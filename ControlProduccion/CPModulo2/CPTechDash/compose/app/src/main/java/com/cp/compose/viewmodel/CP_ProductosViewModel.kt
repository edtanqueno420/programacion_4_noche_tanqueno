package com.cp.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.compose.model.CP_Producto
import com.cp.compose.model.CP_productosDeMuestra
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CP_ProductosViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<CP_Producto>>(emptyList())
    val productos: StateFlow<List<CP_Producto>> = _productos.asStateFlow()

    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda.asStateFlow()

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    private var todosLosProductos: List<CP_Producto> = emptyList()

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        viewModelScope.launch {
            _cargando.value = true
            delay(800)
            todosLosProductos = CP_productosDeMuestra
            _productos.value = todosLosProductos
            _cargando.value = false
        }
    }

    fun actualizarBusqueda(query: String) {
        _busqueda.value = query
        _productos.value = if (query.isBlank()) {
            todosLosProductos
        } else {
            todosLosProductos.filter {
                it.nombre.contains(query, ignoreCase = true) ||
                        it.categoria.contains(query, ignoreCase = true)
            }
        }
    }

    fun recargar() { cargarProductos() }
}
