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

sealed class CP_UiState<out T> {
    object Loading                        : CP_UiState<Nothing>()
    data class Success<T>(val data: T)    : CP_UiState<T>()
    data class Error(val message: String) : CP_UiState<Nothing>()
}

class CP_ProductosUiStateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CP_UiState<List<CP_Producto>>>(CP_UiState.Loading)
    val uiState: StateFlow<CP_UiState<List<CP_Producto>>> = _uiState.asStateFlow()

    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda.asStateFlow()

    private var todosLosProductos: List<CP_Producto> = emptyList()

    init { cargarProductos() }

    fun cargarProductos(simularError: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = CP_UiState.Loading
            delay(800)

            if (simularError) {
                _uiState.value = CP_UiState.Error("Error de conexion (simulado)")
                return@launch
            }

            try {
                todosLosProductos = CP_productosDeMuestra
                _uiState.value = CP_UiState.Success(todosLosProductos)
            } catch (e: Exception) {
                _uiState.value = CP_UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun actualizarBusqueda(query: String) {
        _busqueda.value = query
        val actual = _uiState.value
        if (actual is CP_UiState.Success) {
            _uiState.value = CP_UiState.Success(
                if (query.isBlank()) todosLosProductos
                else todosLosProductos.filter {
                    it.nombre.contains(query, ignoreCase = true) ||
                            it.categoria.contains(query, ignoreCase = true)
                }
            )
        }
    }

    fun recargar() { cargarProductos() }
}
