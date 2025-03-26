package com.alwaygo.alwaygo.data.local.repository

import com.alwaygo.alwaygo.data.remote.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getProducts() = apiService.getProducts()
    suspend fun getCategories() = apiService.getCategories()
}