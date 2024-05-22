package com.example.anarstore


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.anarstore.di.myModule
import com.example.anarstore.ui.theme.AnarStoreTheme
import com.example.anarstore.unit.KEY_CATEGORY_ARG
import com.example.anarstore.unit.KEY_PRODUCT_ARG
import com.example.anarstore.unit.MyScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModule)
            })
            {
                AnarStoreTheme {
                    if (isSystemInDarkTheme()) {
                        SetStatusBarColor(color = Color.White)
                    } else {
                        SetStatusBarColor(color = Color.Black)
                    }
                }
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AnarStoreTheme {

        }
    }

    @Composable
    fun AnarStoreUi() {
        val navController = rememberNavController()

        KoinNavHost(navController = navController, startDestination = MyScreen.MainScreen.route) {

            composable(MyScreen.MainScreen.route) {
                MainScreen()
            }

            composable(MyScreen.IntroScreen.route) {
                IntroScreen()
            }

            composable( route = MyScreen.ProductScreen.route + "/" + "{$KEY_PRODUCT_ARG}" ,
                arguments = listOf(navArgument(KEY_PRODUCT_ARG){
                    type = NavType.StringType
                })
                ) {
                ProductScreen(it.arguments!!.getString(KEY_PRODUCT_ARG , "null"))
            }

            composable( route = MyScreen.CategoryScreen.route + "/" + "{$KEY_CATEGORY_ARG}",
                arguments = listOf(navArgument(KEY_CATEGORY_ARG){
                    type = NavType.StringType
                } )
            ) {
                CategoryScreen(it.arguments!!.getString(KEY_CATEGORY_ARG , "null"))
            }

            composable(MyScreen.ProfileScreen.route) {
                ProfileScreen()
            }

            composable(MyScreen.CartScreen.route) {
                CartScreen()
            }

            composable(MyScreen.SignUpScreen.route) {
                SignUpScreen()
            }

            composable(MyScreen.SignInScreen.route) {
                SignInScreen()
            }

        }

    }

    @Composable
    private fun SignInScreen() {

    }

    @Composable
    private fun SignUpScreen() {

    }

    @Composable
    private fun CartScreen() {

    }

    @Composable
    private fun ProfileScreen() {
        TODO("Not yet implemented")
    }

    @Composable
    private fun CategoryScreen(string: String) {
        TODO("Not yet implemented")
    }

    @Composable
    private fun ProductScreen(string: String) {
        TODO("Not yet implemented")
    }

    @Composable
    private fun IntroScreen() {
        TODO("Not yet implemented")
    }

    @Composable
    fun MainScreen() {
        TODO("Not yet implemented")
    }

    @Composable
    fun SetStatusBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(color)
        }
    }
}