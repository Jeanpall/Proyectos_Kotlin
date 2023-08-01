package com.example.compose_login

sealed class Pantallas (val route: String) {
    object SplashScreen: Pantallas("splash_screen")
    object TiendaApp: Pantallas("tienda_app")
}