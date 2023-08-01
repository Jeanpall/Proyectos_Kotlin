package com.example.compose_login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_login.components.TiendaApp

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Pantallas.SplashScreen.route,
        ) {
        composable(Pantallas.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(Pantallas.TiendaApp.route) {
            TiendaApp()
        }
    }
}