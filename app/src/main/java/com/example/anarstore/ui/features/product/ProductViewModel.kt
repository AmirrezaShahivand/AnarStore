package com.example.anarstore.ui.features.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.repository.product.ProductRepository
import com.example.anarstore.unit.EMPTY_PRODUCT
import com.example.anarstore.unit.coroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Comment

class ProductViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    val thisProduct  = mutableStateOf(EMPTY_PRODUCT)

    fun loadData(productId: String, isInternetConnected: Boolean) {
        loadProductFromCache(productId)
    }

    private fun loadProductFromCache(productId: String) {


        viewModelScope.launch(coroutineExceptionHandler) {
            thisProduct.value = productRepository.getProductById(productId)
        }

    }



}