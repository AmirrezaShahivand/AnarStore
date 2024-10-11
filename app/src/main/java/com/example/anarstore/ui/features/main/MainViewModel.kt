package com.example.anarstore.ui.features.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anarstore.model.data.Ads
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.repository.product.ProductRepository
import com.example.anarstore.unit.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val productRepository: ProductRepository,
    isInternetConnected: Boolean
) : ViewModel() {


    val dataProduct = mutableStateOf<List<Product>>(listOf())
    val dataAds = mutableStateOf<List<Ads>>(listOf())
    val showProgressBar = mutableStateOf(false)



    init {
        refreshAllDataFromNet(isInternetConnected)
    }








    private fun refreshAllDataFromNet(isInternetConnected: Boolean) {

        viewModelScope.launch(coroutineExceptionHandler) {
            if (isInternetConnected)
                showProgressBar.value = true

            delay(1000)

            val newDataProduct = async { productRepository.getAllProducts(isInternetConnected) }
            val newDataAds = async { productRepository.getAllAds(isInternetConnected) }
            updateData(newDataProduct.await(), newDataAds.await())

            showProgressBar.value = false

        }

    }

    private fun updateData(product: List<Product>, ads: List<Ads>) {
        dataProduct.value = product
        dataAds.value = ads

    }

    }

