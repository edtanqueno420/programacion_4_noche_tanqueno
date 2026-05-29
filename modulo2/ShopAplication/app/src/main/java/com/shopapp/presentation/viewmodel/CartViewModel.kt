// presentation/viewmodel/CartViewModel.kt
package com.shopapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.shopapp.domain.model.Product
import com.shopapp.domain.repository.OrderRepository
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CheckoutState {
    object Idle : CheckoutState()
    object Loading : CheckoutState()
    data class Success(val orderId: Int) : CheckoutState()
    data class Error(val message: String) : CheckoutState()
}

data class CartItem(
    val product:  Product,
    val quantity: Int,
)

@HiltViewModel
class CartViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()

    private val _checkoutState = MutableStateFlow<CheckoutState>(CheckoutState.Idle)
    val checkoutState: StateFlow<CheckoutState> = _checkoutState.asStateFlow()

    val totalItems: StateFlow<Int> = _items
        .map { list -> list.sumOf { it.quantity } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val subtotal: StateFlow<Double> = _items
        .map { list -> list.sumOf { it.product.price * it.quantity } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    val totalWithTax: StateFlow<Double> = _items
        .map { list -> list.sumOf { it.product.priceWithTax * it.quantity } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    fun addItem(product: Product, quantity: Int = 1) {
        _items.update { list ->
            val existing = list.find { it.product.id == product.id }
            if (existing != null) {
                list.map {
                    if (it.product.id == product.id)
                        it.copy(quantity = minOf(it.quantity + quantity, product.stock))
                    else it
                }
            } else {
                list + CartItem(product, quantity)
            }
        }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        if (quantity <= 0) removeItem(productId)
        else _items.update { list ->
            list.map { if (it.product.id == productId) it.copy(quantity = quantity) else it }
        }
    }

    fun removeItem(productId: Int) {
        _items.update { list -> list.filter { it.product.id != productId } }
    }

    fun clearCart() { _items.value = emptyList() }

    fun resetCheckout() {
        _checkoutState.value = CheckoutState.Idle
    }

    fun checkout() {
        val currentItems = _items.value
        if (currentItems.isEmpty()) return

        _checkoutState.value = CheckoutState.Loading
        viewModelScope.launch {
            orderRepository.createOrder().onSuccess { order ->
                var success = true
                for (item in currentItems) {
                    orderRepository.addItem(order.id, item.product.id, item.quantity).onFailure { e ->
                        success = false
                        _checkoutState.value = CheckoutState.Error("Error al añadir ${item.product.name}: ${e.message}")
                    }
                    if (!success) break
                }

                if (success) {
                    orderRepository.confirmOrder(order.id).onSuccess {
                        _items.value = emptyList()
                        _checkoutState.value = CheckoutState.Success(order.id)
                    }.onFailure { e ->
                        _checkoutState.value = CheckoutState.Error(e.message ?: "Error al confirmar pedido")
                    }
                }
            }.onFailure { e ->
                _checkoutState.value = CheckoutState.Error(e.message ?: "Error al crear pedido")
            }
        }
    }
}