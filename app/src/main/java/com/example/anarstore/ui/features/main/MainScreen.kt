package com.example.anarstore.ui.features.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.anarstore.SetStatusBarColor
import com.example.anarstore.model.data.Ads
import com.example.anarstore.model.data.Product
import com.example.anarstore.ui.theme.AnarStoreTheme
import com.example.anarstore.ui.theme.BackgroundMain
import com.example.anarstore.ui.theme.Blue
import com.example.anarstore.ui.theme.CardViewBackground
import com.example.anarstore.unit.CATEGORY
import com.example.anarstore.unit.MyScreen
import com.example.anarstore.unit.NetworkChecker
import com.example.anarstore.unit.TAGS
import com.example.anarstore.unit.stylePrice
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import org.koin.core.parameter.parametersOf

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AnarStoreTheme {
        Surface(
            color = BackgroundMain, modifier = Modifier.fillMaxSize()
        ) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    if (isSystemInDarkTheme()) {
        SetStatusBarColor(color = Color.White)
    } else {
        SetStatusBarColor(color = Color.White)
    }

    val viewModel = getNavViewModel<MainViewModel>(
        parameters = {
            parametersOf(NetworkChecker(context).isInternetConnected)
        }
    )
    val navigation = getNavController()



    Box {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {

            if (viewModel.showProgressBar.value) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Blue
                )
            }
            TopToolbar(badgeNumber = viewModel.badgeNumber.value, onCartClicked = {
                if (NetworkChecker(context).isInternetConnected)
                    navigation.navigate(MyScreen.CartScreen.route)
                else
                    Toast.makeText(context, "please connect to internet", Toast.LENGTH_SHORT).show()
            },
                onProfileClicked = {
                    navigation.navigate(MyScreen.ProfileScreen.route)
                })

            CategoryBar(CATEGORY) {
                navigation.navigate(MyScreen.CategoryScreen.route + "/" + it)
            }

            val productDataState = viewModel.dataProduct
            val adsDataState = viewModel.dataAds
            ProductSubjectList(TAGS, productDataState.value, adsDataState.value) {
                navigation.navigate(MyScreen.ProductScreen.route + "/" + it)
            }

        }
    

    
    }

}



@Composable
fun ProductSubjectList(
    tags: List<String>,
    product: List<Product>,
    ads: List<Ads>,
    onProductClicked: (String) -> Unit
) {


    if (product.isNotEmpty()) {

        Column {
            tags.forEachIndexed { it, _ ->
                val withTagData = product.filter { product -> product.tags == tags[it] }
                ProductSubject(tags[it], withTagData.shuffled(), onProductClicked)

                if (ads.size >= 2)
                    if (it == 1 || it == 2)
                        BigPictureTablighat(ads[it - 1], onProductClicked)

            }
        }
    }

}

@Composable
fun BigPictureTablighat(ads: Ads, onProductClicked: (String) -> Unit) {

    AsyncImage(
        model = ads.imageURL,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .clip(shapes.medium)
            .clickable { onProductClicked.invoke(ads.productId) },
        contentScale = ContentScale.Crop
    )

}

@Composable
fun ProductSubject(subject: String, data: List<Product>, onProductClicked: (String) -> Unit) {

    Column(
        modifier = Modifier.padding(top = 32.dp)
    ) {

        Text(
            text = subject,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.labelLarge
        )

        ProductBar(data, onProductClicked)

    }

}

@Composable
fun ProductBar(data: List<Product>, onProductClicked: (String) -> Unit) {


    LazyRow(modifier = Modifier.padding(top = 16.dp), contentPadding = PaddingValues(end = 16.dp)) {


        items(data.size) {
            ProductItem(data[it], onProductClicked)
        }


    }


}

@Composable
fun ProductItem(product: Product, onProductClicked: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { onProductClicked.invoke(product.productId) },
        shape = shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column {
            AsyncImage(
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop,
                model = product.imgUrl,
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = product.name,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)
                )
                Text(
                    text = stylePrice(product.price),
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)
                )
                Text(
                    text = product.soldItem,
                    style = TextStyle(color = Color.Gray, fontSize = 13.sp)
                )
            }


        }

    }
}

@Composable
fun CategoryBar(categoryList: List<Pair<String, Int>>, ocCategoryClicked: (String) -> Unit) {

    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        items(categoryList.size) {
            CategoryItem(categoryList[it], ocCategoryClicked)
        }
    }

}

@Composable
fun CategoryItem(subject: Pair<String, Int>, ocCategoryClicked: (String) -> Unit) {

    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { ocCategoryClicked.invoke(subject.first) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(shape = shapes.medium, color = CardViewBackground) {

            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = subject.second),
                contentDescription = null
            )

        }

        Text(
            text = subject.first,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(color = Color.Gray)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolbar(
    badgeNumber: Int
    ,onCartClicked: () -> Unit ,
     onProfileClicked: () -> Unit
) {


    TopAppBar(
        title = {
            Text(text = "TopApp Bar")
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        actions = {


            IconButton(
                modifier = Modifier.padding(end = 6.dp),
                onClick = { onCartClicked.invoke() }
            ) {

                if (badgeNumber == 0) {
                    Icon(Icons.Default.ShoppingCart, null)
                } else {

                    BadgedBox(badge = { Badge { Text(badgeNumber.toString()) } }) {
                        Icon(Icons.Default.ShoppingCart, null)
                    }
                }
            }

            IconButton(onClick = { onProfileClicked.invoke() }) {
                Icon(Icons.Default.Person, contentDescription = null)
            }


        }
    )
}