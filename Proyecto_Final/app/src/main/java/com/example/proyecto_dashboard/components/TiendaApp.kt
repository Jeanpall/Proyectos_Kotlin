package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto_dashboard.pages.login.LoginScreen
import com.example.proyecto_dashboard.pages.login.Pantallas
import com.example.proyecto_dashboard.pages.login.SplashScreen

//Definicion de una clase
enum class PageScreen(){
    Login,
    DashBoard
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController
) {

    //Navegación dentro de la app
    Scaffold() { padding -> ScaffoldContent(
        padding = padding)
        NavHost(
            navController = navController,
            startDestination = Pantallas.SplashScreen.ruta
        ){
            /**
             * [composable] para el splash y el inicio de sesion
             */
            composable(Pantallas.SplashScreen.ruta) {
                SplashScreen(navController)
            }
            composable(route = PageScreen.Login.name){
                LoginScreen(navController = navController)
            }
            composable(route = MenuItem.Page01.ruta){
                MainPage()
            }
        }
    }
}

@Composable
fun ScaffoldContent(//1
    padding: PaddingValues,
) {
    Column(//(2)
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = padding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row { // (3)
        }
    }
}