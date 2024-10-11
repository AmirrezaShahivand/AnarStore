package com.example.anarstore.model.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity("product_table")
data class Product(

    @PrimaryKey
    val productId: String,

    val name: String,
    val img: String,
    val detailText: String,
    val price: String,
    val category: String,
    val quantity: String?
)