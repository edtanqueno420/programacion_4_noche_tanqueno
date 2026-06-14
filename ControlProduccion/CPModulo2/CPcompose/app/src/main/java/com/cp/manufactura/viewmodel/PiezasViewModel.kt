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

class PiezasViewModel : ViewModel() {

    private val _piezas = MutableStateFlow<List<Pieza>>(emptyList())
    val piezas: StateFlow<List<Pieza>> = _piezas.asStateFlow()

    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda.asStateFlow()

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    private var todasLasPiezas: List<Pieza> = emptyList()

    init {
        cargarPiezas()
    }

    private fun cargarPiezas() {
        viewModelScope.launch {
            _cargando.value = true
            delay(800)
            todasLasPiezas = piezasDeMuestra
            _piezas.value = todasLasPiezas
            _cargando.value = false
        }
    }

    fun actualizarBusqueda(query: String) {
        _busqueda.value = query
        _piezas.value = if (query.isBlank()) {
            todasLasPiezas
        } else {
            todasLasPiezas.filter {
                it.nombre.contains(query, ignoreCase = true) ||
                        it.categoria.contains(query, ignoreCase = true)
            }
        }
    }

    fun recargar() { cargarPiezas() }
}
