package com.example.anarstore.model.data

data class Product(


    val productId: String,
    val name: String,
    val imgUrl: String,
    val detailText: String,
    val price: String,
    val soldItem: String,
    val category: String,
    val material: String,
    val tags: String ,
    val quantity : String?
)