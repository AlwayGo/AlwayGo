package com.alwaygo.alwaygo.data.remote.api

import com.alwaygo.alwaygo.data.remote.model.Category
import com.alwaygo.alwaygo.data.remote.model.Product
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("categories")
    suspend fun getCategories(): List<Category>
}