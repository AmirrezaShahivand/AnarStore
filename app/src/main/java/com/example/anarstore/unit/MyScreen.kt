package com.example.anarstore.unit

sealed class MyScreen(val route: String) {
    data object MainScreen : MyScreen("mainScreen")
    data object IntroScreen : MyScreen("introScreen")
    data object ProductScreen : MyScreen("productScreen")
    data object CategoryScreen : MyScreen("categoryScreen")
    data object ProfileScreen : MyScreen("profileScreen")
    data object CartScreen : MyScreen("cartScreen")
    data object SignUpScreen : MyScreen("signUpScreen")
    data object SignInScreen : MyScreen("signInScreen")
    data object SearchScreen : MyScreen("searchScreen")
}