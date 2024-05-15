package com.example.foodies.ui.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.model.Product
import com.example.foodies.data.network.FoodiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: FoodiesRepository
) : ViewModel() {

    private var products = listOf<Product>()

    init {
        getProducts()
    }

    fun addProductToCart(id: Int) {
        repository.addProductToCart(id)
    }

    fun getProductById(id: Int): Product {
        return products.find { it.id == id }!!
    }

    private fun getProducts() {
        viewModelScope.launch {
            products = repository.getProducts().getOrNull() ?: listOf()
        }
    }
}