package com.cp.manufactura.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.manufactura.model.Pieza
import com.cp.manufactura.model.piezasDeMuestra
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Loading                        : UiState<Nothing>()
    data class Success<T>(val data: T)    : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class PiezasUiStateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Pieza>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pieza>>> = _uiState.asStateFlow()

    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda.asStateFlow()

    private var todasLasPiezas: List<Pieza> = emptyList()

    init { cargarPiezas() }

    fun cargarPiezas(simularError: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            delay(800)

            if (simularError) {
                _uiState.value = UiState.Error("Error de conexión (simulado)")
                return@launch
            }

            try {
                todasLasPiezas = piezasDeMuestra
                _uiState.value = UiState.Success(todasLasPiezas)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun actualizarBusqueda(query: String) {
        _busqueda.value = query
        val actual = _uiState.value
        if (actual is UiState.Success) {
            _uiState.value = UiState.Success(
                if (query.isBlank()) todasLasPiezas
                else todasLasPiezas.filter {
                    it.nombre.contains(query, ignoreCase = true) ||
                            it.categoria.contains(query, ignoreCase = true)
                }
            )
        }
    }

    fun recargar() { cargarPiezas() }
}
