package com.example.anarstore.model.repository.product

import com.example.anarstore.model.data.Ads
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.db.ProductDao
import com.example.anarstore.model.net.ApiService
import com.example.anarstore.unit.EMPTY_PRODUCT

class ProductRepositoryImpl(
    private val productDao : ProductDao,

    ) : ProductRepository {


}