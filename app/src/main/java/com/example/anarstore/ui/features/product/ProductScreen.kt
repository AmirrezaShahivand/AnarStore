package com.example.anarstore.ui.features.product

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.sqlite.db.SupportSQLiteOpenHelper
import coil.compose.AsyncImage
import com.example.anarstore.R
import com.example.anarstore.model.data.Product
import com.example.anarstore.ui.SetStatusBarColor
import com.example.anarstore.ui.theme.AnarStoreTheme
import com.example.anarstore.ui.theme.BackgroundMain
import com.example.anarstore.ui.theme.Blue
import com.example.anarstore.ui.theme.PriceBackground
import com.example.anarstore.unit.MyScreen
import com.example.anarstore.unit.NetworkChecker
import com.example.anarstore.unit.stylePrice
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    AnarStoreTheme {
        Surface(
            color = BackgroundMain, modifier = Modifier.fillMaxSize()
        ) {
            ProductScreen("")
        }
    }
}

@Composable
fun ProductScreen(productId: String) {
    val context = LocalContext.current

    if (isSystemInDarkTheme()) {
        SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    } else {
        SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    }

    val viewModel = getNavViewModel<ProductViewModel>()
    viewModel.loadData(productId, NetworkChecker(context).isInternetConnected)


    val navigation = getNavController()


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
            ,
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 58.dp)
            ) {
                ProductToolbar(
                    productName = "Details",
                    onBackClicked = { navigation.popBackStack() },
                    onCartClicked = {
                        if (NetworkChecker(context).isInternetConnected) {
                            navigation.navigate(MyScreen.CartScreen.route)
                        } else {
                            Toast.makeText(context, "please connect to internet!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                )


                ProductItem(
                    data = viewModel.thisProduct.value,
                    onCategoryClicked = { navigation.navigate(MyScreen.CategoryScreen.route + "/" + it) },
                )
            }

        }
    }


}

@Composable
fun ProductItem(
    data: Product,
    onCategoryClicked: (String) -> Unit,
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ProductDesign(data, onCategoryClicked)

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 14.dp, bottom = 14.dp)
        )

        ProductDetail(data)

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 14.dp, bottom = 14.dp)
        )


    }

}


@Composable
fun ProductDetail(data: Product) {
    val context = LocalContext.current


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_material),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = data.quantity.toString(),
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 13.sp ,
                    style = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                )
            }


        }


    }

}


@Composable
fun ProductDesign(data: Product, onCategoryClicked: (String) -> Unit) {
    AsyncImage(
        model = data.img, contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(shapes.large)
    )

    Text(
        text = data.name,
        modifier = Modifier.padding(top = 14.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium ,
            color = MaterialTheme.colorScheme.onPrimary
        )
    )

    Text(
        text = data.detailText,
        modifier = Modifier.padding(top = 4.dp),
        style = TextStyle(fontSize = 15.sp, textAlign = TextAlign.Justify , color = MaterialTheme.colorScheme.onPrimary)
    )

    TextButton(onClick = { onCategoryClicked.invoke(data.category) }) {
        Text(
            text =  data.category + "#",
            style = TextStyle(fontSize = 13.sp , color = MaterialTheme.colorScheme.onPrimary)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductToolbar(
    productName: String,
    onBackClicked: () -> Unit,
    onCartClicked: () -> Unit
) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onBackClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.fillMaxWidth(),
        title = {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                text = productName,
                textAlign = TextAlign.Center
            )
        },
        actions = {

            IconButton(
                modifier = Modifier.padding(end = 6.dp),
                onClick = { onCartClicked.invoke() }
            ) {

            }
        }
    )
}


@Composable
fun AddToCart(
    price: String,
    isAddingProduct: Boolean,
    onCartClicked: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val fraction =
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.15f else 0.07f


    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(182.dp, 40.dp),
                onClick = { onCartClicked.invoke() }
            ) {

                if (isAddingProduct) {
                    DotsTyping()
                } else {
                    Text(
                        text = "Add Product To Cart",
                        modifier = Modifier.padding(2.dp),
                        color = Color.White,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                }

            }

            Surface(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clip(shapes.large),
                color = PriceBackground
            )
            {
                Text(
                    text = stylePrice(price),
                    style = TextStyle(fontSize = 14.sp),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
                )
            }

        }
    }

}


@Composable
fun DotsTyping() {

    val dotSize = 10.dp
    val delayUnit = 350
    val maxOffset = 10f

    @Composable
    fun Dot(
        offset: Float
    ) = Spacer(
        Modifier
            .size(dotSize)
            .offset(y = -offset.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .padding(start = 8.dp, end = 8.dp)
    )

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun animateOffsetWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 4
                0f at delay with LinearEasing
                maxOffset at delay + delayUnit with LinearEasing
                0f at delay + delayUnit * 2
            }
        )
    )

    val offset1 by animateOffsetWithDelay(0)
    val offset2 by animateOffsetWithDelay(delayUnit)
    val offset3 by animateOffsetWithDelay(delayUnit * 2)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = maxOffset.dp)
    ) {
        val spaceSize = 2.dp

        Dot(offset1)
        Spacer(Modifier.width(spaceSize))
        Dot(offset2)
        Spacer(Modifier.width(spaceSize))
        Dot(offset3)
    }
}
