package com.example.anarstore.unit

import com.example.anarstore.R
import com.example.anarstore.model.data.Product

const val KEY_PRODUCT_ARG = "productId"
const val KEY_CATEGORY_ARG = "categoryName"

const val VALUE_SUCCESS = "success"

val CATEGORY = listOf(
    Pair("لاک ناخن", R.drawable.lak),
    Pair("رژ لب", R.drawable.rozh),
    Pair("میکا‍پ", R.drawable.makeup),
    Pair("محصولات موی سر", R.drawable.hair),
    Pair("کیف", R.drawable.bag),
    Pair("عطر", R.drawable.atr)
)

val TAGS = listOf(
    "جدید ترین",
    "بهترین ها",
    "پر طرفدار",
    "بالاترین کیفیت"
)


val EMPTY_PRODUCT = Product("55" , "645455" , "" , "vkjdnjvjdsknvlknsdlvnksdnvndsjklnv" , "51151" , "212" , "fndjknd" , "vlkmnf" , "c,lndsjk" , "")
