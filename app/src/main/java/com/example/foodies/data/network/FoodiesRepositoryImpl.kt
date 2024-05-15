package com.example.foodies.data.network

import com.example.foodies.data.model.Category
import com.example.foodies.data.model.Product
import com.example.foodies.data.model.Tag
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val MAX_PRODUCTS_PER_ORDER = 10

class FoodiesRepositoryImpl(
    private val foodiesApi: FoodiesApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): FoodiesRepository {

    // Local cache
    private val productCart: MutableMap<Product, Int> = mutableMapOf()
    private var productList: MutableList<Product> = mutableListOf()

    override fun getProductCart(): Map<Product, Int> {
        return productCart.toMap()
    }

    override fun addProductToCart(id: Int, quantity: Int): Map<Product, Int> {
        val product = productList.find { it.id == id }!!
        val currentQuantity = productCart.getOrDefault(product, 0)
        if (currentQuantity + quantity <= MAX_PRODUCTS_PER_ORDER) {
            productCart[product] = currentQuantity + quantity
        }
        return productCart.toMap()
    }

    override fun removeProductFromCart(id: Int, quantity: Int): Map<Product, Int> {
        val product = productList.find { it.id == id }!!
        val currentQuantity = productCart.getOrDefault(product, 0)
        if (currentQuantity - quantity <= 0) {
            productCart.remove(product)
        } else {
            productCart[product] = currentQuantity - quantity
        }
        return productCart.toMap()
    }

    override suspend fun getCategories(): Result<List<Category>> = makeApiCall(dispatcher) {
            foodiesApi.getCategories()
    }

    override suspend fun getProducts(): Result<List<Product>> {
        return if (productList.isNotEmpty()){
            Result.success(productList)
        } else {
            makeApiCall(dispatcher) {
                foodiesApi.getProducts()
            }.onSuccess {
                productList = it.toMutableList()
            }
        }
    }

    override suspend fun getTags(): Result<List<Tag>> = makeApiCall(dispatcher) {
            foodiesApi.getTags()
    }

}

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        call.invoke()
    }
}