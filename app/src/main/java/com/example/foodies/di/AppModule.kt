package com.example.foodies.di

import com.example.foodies.data.network.FoodiesApi
import com.example.foodies.data.network.FoodiesRepository
import com.example.foodies.data.network.FoodiesRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodiesApi(): FoodiesApi {
        return Retrofit.Builder()
            .baseUrl("https://anika1d.github.io/WorkTestServer/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FoodiesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodiesRepository(api: FoodiesApi): FoodiesRepository {
        return FoodiesRepositoryImpl(api)
    }
}