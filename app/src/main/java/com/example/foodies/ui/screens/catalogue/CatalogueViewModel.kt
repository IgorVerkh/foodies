package com.example.foodies.ui.screens.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.model.Category
import com.example.foodies.data.model.Product
import com.example.foodies.data.network.FoodiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val repository: FoodiesRepository
) : ViewModel() {
    private var products = listOf<Product>()

    private val _productList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productList: StateFlow<List<Product>> = _productList.asStateFlow()

    private val _categoryList: MutableStateFlow<List<Category>> = MutableStateFlow(listOf(Category(0, "Все")))
    val categoryList: StateFlow<List<Category>> = _categoryList.asStateFlow()

    private val _productCart: MutableStateFlow<Map<Product, Int>> = MutableStateFlow(emptyMap())
    val productCart: StateFlow<Map<Product, Int>> = _productCart.asStateFlow()

    init {
        getProducts()
        getCategories()
        refreshProductCart()
    }

    fun addProductToCart(id: Int) {
        _productCart.value = repository.addProductToCart(id)
    }
    fun removeProductFromCart(id: Int) {
        _productCart.value = repository.removeProductFromCart(id)
    }

    // TODO: get rid of that
    fun setCategoryFilter(category: Category) {
        _productList.value = products.filter { (it.categoryId == category.id) or (category.id == 0) }
    }

    fun refreshProductCart() {
        viewModelScope.launch {
            _productCart.value = repository.getProductCart()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            products = repository.getProducts().getOrNull() ?: listOf()
            _productList.value = products
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoryList.value = repository.getCategories().getOrNull() ?: listOf()
            _categoryList.value = listOf(Category(0, "Все")) + _categoryList.value
        }
    }

}