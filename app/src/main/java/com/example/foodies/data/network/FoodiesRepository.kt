package com.example.foodies.data.network

import com.example.foodies.data.model.Category
import com.example.foodies.data.model.Product
import com.example.foodies.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface FoodiesRepository {
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getTags(): Result<List<Tag>>
    fun getProductCart(): Map<Product, Int>
    fun addProductToCart(id: Int, quantity: Int = 1): Map<Product, Int>
    fun removeProductFromCart(id: Int, quantity: Int = 1): Map<Product, Int>
}