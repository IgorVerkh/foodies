package com.example.foodies.data.network

import com.example.foodies.data.model.Category
import com.example.foodies.data.model.Product
import com.example.foodies.data.model.Tag
import retrofit2.http.GET

interface FoodiesApi {

    @GET(Endpoints.GET_CATEGORIES)
    suspend fun getCategories(): List<Category>

    @GET(Endpoints.GET_PRODUCTS)
    suspend fun getProducts(): List<Product>

    @GET(Endpoints.GET_TAGS)
    suspend fun getTags(): List<Tag>
}