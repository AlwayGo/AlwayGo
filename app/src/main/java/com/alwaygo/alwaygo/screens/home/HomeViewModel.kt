package com.alwaygo.alwaygo.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alwaygo.alwaygo.data.local.repository.HomeRepository
import com.alwaygo.alwaygo.data.remote.model.Category
import com.alwaygo.alwaygo.data.remote.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        fetchProducts()
        fetchCategories()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = repository.getProducts()
                _products.postValue(response)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching products: ${e.message}")
            }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _categories.postValue(response)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching categories: ${e.message}")
            }
        }
    }
}