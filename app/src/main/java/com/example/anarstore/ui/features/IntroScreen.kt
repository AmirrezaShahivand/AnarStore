package com.example.anarstore.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.anarstore.R
import com.example.anarstore.ui.SetStatusBarColor
import com.example.anarstore.ui.theme.AnarStoreTheme
import com.example.anarstore.ui.theme.BackgroundMain
import com.example.anarstore.unit.MyScreen
import dev.burnoo.cokoin.navigation.getNavController
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    AnarStoreTheme {
        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            IntroScreen()


        }
    }
}

@Composable
fun IntroScreen(){
    val navigation = getNavController()
    if (isSystemInDarkTheme()) {
        SetStatusBarColor(color = Color.Blue)
    } else {
        SetStatusBarColor(color = Color.Blue)
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.img_intro) ,
        contentDescription = null  ,
        contentScale = ContentScale.Crop
    )

    LaunchedEffect(Unit) {
        delay(650)
        navigation.navigate(MyScreen.MainScreen.route)
    }

    }

