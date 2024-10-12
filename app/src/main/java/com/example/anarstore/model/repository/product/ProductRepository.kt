package com.example.anarstore.model.repository.product

import com.example.anarstore.model.data.Ads
import com.example.anarstore.model.data.Product
import com.parse.ParseObject
import com.parse.ParseQuery


interface ProductRepository {

    suspend fun getAllProducts(isInternetConnected : Boolean) : List<Product>

    suspend fun getAllAds(isInternetConnected : Boolean) : List<Ads>

    suspend fun getAllProductsByCategory(category: String) : List<Product>



}