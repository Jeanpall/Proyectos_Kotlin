package com.example.proyecto_dashboard.pages.login

/**
 * Define una clase sellada [Pantallas] que tiene una propiedad ruta
 */
sealed class Pantallas (val ruta: String) {
    //Subclases que hereda de la clase Pantallas
    object SplashScreen: Pantallas("splash_screen")
    object TiendaApp: Pantallas("tienda_app")
}