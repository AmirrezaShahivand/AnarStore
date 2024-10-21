package com.example.anarstore.ui.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.anarstore.model.data.Product
import com.example.anarstore.ui.SetStatusBarColor
import com.example.anarstore.ui.features.main.ProductItem
import com.example.anarstore.ui.theme.AnarStoreTheme
import com.example.anarstore.ui.theme.Blue
import com.example.anarstore.unit.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    AnarStoreTheme {
        Surface(
            color = Blue, modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = getNavViewModel()

    val navigation = getNavController()

    if (isSystemInDarkTheme()) {
        SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    } else {
        SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    }

    val searchResults by viewModel.searchResults.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column {

            ProductSearchField(searchQuery = searchQuery) { newQuery ->
                searchQuery = newQuery
                viewModel.setSearchQuery(newQuery)
            }

            ProductBar(products = searchResults) {
                navigation.navigate(MyScreen.ProductScreen.route + "/" + it)
            }
        }
    }
}


@Composable
fun ProductBar(
    products: List<Product>,
    onProductClicked: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product, onProductClicked = onProductClicked)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchField(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit ,
){
    TextField(
        value = searchQuery,
        onValueChange = { newQuery ->
            onSearchQueryChange(newQuery)
        },
        placeholder = { Text("جستجو...") },
        modifier = Modifier.fillMaxWidth() ,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Blue, // رنگ خط زیرین در حالت تمرکز
            unfocusedIndicatorColor = Color.Transparent // رنگ خط زیرین در حالت عدم تمرکز
        )
    )
}