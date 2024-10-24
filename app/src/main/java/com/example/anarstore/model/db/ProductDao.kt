package com.example.anarstore.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anarstore.model.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertOrUpdate(products : List<Product>)


    @Query("SELECT * FROM product_table")
    suspend fun getAll() : List<Product>

    @Query("SELECT * FROM product_table WHERE category LIKE :category")
    suspend fun getAllByCategory(category : String) :List <Product>

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    suspend fun getProductById(productId : String) : Product

    @Query("SELECT * FROM product_table WHERE name LIKE :query OR category LIKE :query")
     fun searchProducts(query: String): Flow<List<Product>>



}