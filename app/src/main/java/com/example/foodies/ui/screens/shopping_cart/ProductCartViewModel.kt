package com.example.foodies.ui.screens.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.model.Product
import com.example.foodies.data.network.FoodiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCartViewModel @Inject constructor(
    private val repository: FoodiesRepository
) : ViewModel() {
    private val _productCart: MutableStateFlow<Map<Product, Int>> = MutableStateFlow(emptyMap())
    val productCart: StateFlow<Map<Product, Int>> = _productCart.asStateFlow()

    fun addProductToCart(id: Int) {
        _productCart.value = repository.addProductToCart(id)
    }
    fun removeProductFromCart(id: Int) {
        _productCart.value = repository.removeProductFromCart(id)
    }

    fun refreshProductCart() {
        viewModelScope.launch {
            _productCart.value = repository.getProductCart()
        }
    }
}