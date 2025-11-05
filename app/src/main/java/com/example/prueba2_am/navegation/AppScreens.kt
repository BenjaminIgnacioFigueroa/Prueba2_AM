package com.example.prueba2_am.navegation


sealed class AppScreens(val route: String) {
    object Home : AppScreens("home_screen")
    object Register : AppScreens("register_screen")
    object Login : AppScreens("login_screen")
    object Products : AppScreens("products_screen")
}