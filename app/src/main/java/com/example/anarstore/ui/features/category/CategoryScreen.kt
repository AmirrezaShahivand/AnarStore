package com.example.anarstore.ui.features.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.anarstore.model.data.Product
import com.example.anarstore.unit.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@Composable
fun CategoryScreen(categoryName: String) {

    val viewModel = getNavViewModel<CategoryViewModel>()
    viewModel.loadDataByCategory(categoryName)

    val navigation = getNavController()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        CategoryToolbar(categoryName)

        val data = viewModel.dataProduct

        CategoryList(data.value) {
            navigation.navigate(MyScreen.ProductScreen.route + "/" + it)
        }

    }
}

@Composable
fun CategoryItem(data: Product, onProductClicked: (String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onProductClicked.invoke(data.productId) }
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = shapes.medium ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {

        Column {
            AsyncImage(
                model = data.img, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = data.name,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium , color = MaterialTheme.colorScheme.onPrimary)
                    )

                    Text(
                        text = data.price + " Tomans",
                        modifier = Modifier.padding(top = 4.dp),
                        style = TextStyle(fontSize = 14.sp , color = MaterialTheme.colorScheme.onPrimary )
                    )

                }

            }

        }

    }

}

@Composable
fun CategoryList(data: List<Product>, onProductClicked: (String) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxSize() , contentPadding = PaddingValues(bottom = 8.dp)){
        items(data.size){
            CategoryItem(data = data[it], onProductClicked = onProductClicked)
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryToolbar(categoryName: String) {

    TopAppBar(title = {
        Text(text = categoryName)
    },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary ,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.fillMaxWidth()


    )

}
