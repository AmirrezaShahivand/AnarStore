package com.example.anarstore.di

import androidx.room.Room
import com.example.anarstore.model.db.AppDatabase
import com.example.anarstore.model.repository.product.ProductRepository
import com.example.anarstore.model.repository.product.ProductRepositoryImpl
import com.example.anarstore.ui.features.main.MainViewModel
import com.example.anarstore.ui.features.product.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {


    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build()
    }


    single<ProductRepository> { ProductRepositoryImpl(get<AppDatabase>().productDao()) }



    viewModel { ProductViewModel(get()) }
    viewModel { (isInternetConnected: Boolean) -> MainViewModel(get(), isInternetConnected) }


}