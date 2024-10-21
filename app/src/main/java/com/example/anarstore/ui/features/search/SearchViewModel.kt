package com.example.anarstore.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anarstore.model.data.Product
import com.example.anarstore.model.repository.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class SearchViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchResults: StateFlow<List<Product>> = _searchQuery
        .debounce(300) // تاخیر برای کاهش درخواست‌ها به دیتابیس
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                flowOf(emptyList()) // اگر رشته جستجو خالی باشد، لیست خالی برگردان
            } else {
                productRepository.searchProducts("%$query%")
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }



}