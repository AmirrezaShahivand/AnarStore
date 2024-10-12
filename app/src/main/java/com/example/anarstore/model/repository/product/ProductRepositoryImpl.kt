package com.example.anarstore.model.repository.product

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.rememberCoroutineScope
import com.example.anarstore.model.data.Ads
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.db.ProductDao
import com.example.anarstore.unit.CLASS_ADS_SERVER
import com.example.anarstore.unit.CLASS_PRODUCT_SERVER
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import java.io.File
import kotlin.coroutines.suspendCoroutine


class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getAllProducts(isInternetConnected: Boolean): List<Product> {

        val productList = mutableListOf<Product>()

        if (isInternetConnected) {

            val deferred = CompletableDeferred<List<Product>>()

            val query = ParseQuery<ParseObject>(CLASS_PRODUCT_SERVER)

            query.findInBackground { objects, e ->

                if (e == null) {

                    objects.forEach {

                        val id = it.objectId
                        val name = it.getString("name")
                        val parseFile: ParseFile? = it.getParseFile("image")
                        val img: String? = parseFile?.url
                        val description = it.getString("description")
                        val price = it.getString("price")
                        val category = it.getString("category")
                        val quantity = it.getString("quantity")


                        val product =
                            Product(id, name!!, img!!, description!!, price!!, category!!, quantity)

                        productList.add(product)

                    }
                    deferred.complete(productList)
                }

            }
            productDao.insertOrUpdate(deferred.await())
            return deferred.await()
        } else {
            return productDao.getAll()
        }

    }

    override suspend fun getAllAds(isInternetConnected: Boolean): List<Ads> {

        val adsList = mutableListOf<Ads>()

        if (isInternetConnected) {

            val query = ParseQuery<ParseObject>(CLASS_ADS_SERVER)

            query.findInBackground { objects, e ->

                if (e == null) {

                    objects.forEach {

                        val id = it.objectId
                        val name = it.getString("name")
                        val parseFile: ParseFile? = it.getParseFile("image")
                        val img: String? = parseFile?.url

                        val ads = Ads(id, img!!, name!!)

                        adsList.add(ads)

                    }


                }


            }

            return adsList

        }
        return listOf()
    }

    override suspend fun getAllProductsByCategory(category: String): List<Product> {

        val x = productDao.getAllByCategory(category)
        return x
    }

}

