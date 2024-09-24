package com.example.anarstore.ui.features.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.repository.product.ProductRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {


    val dataProduct = mutableStateOf<List<Product>>(listOf())


    fun loadDataByCategory(category: String) {



    }



}