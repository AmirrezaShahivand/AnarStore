package com.example.anarstore.model.data

data class AdsResponse(
    val success : Boolean ,
    val ads : List<Ads>
)


data class Ads(
    val adId : String ,
    val name : String ,
    val imageURL : String ,
    val productId : String
)
