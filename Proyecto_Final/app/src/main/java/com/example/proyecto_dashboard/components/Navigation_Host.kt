package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyecto_dashboard.pages.HomeScreen
import com.example.proyecto_dashboard.pages.Page_Flores
import com.example.proyecto_dashboard.pages.Page_Frutas_Verduras
import com.example.proyecto_dashboard.pages.Page_Huevos
import com.example.proyecto_dashboard.pages.Page_Informacion
import com.example.proyecto_dashboard.pages.Page_Inicio
import com.example.proyecto_dashboard.pages.Page_Lacteos
import com.example.proyecto_dashboard.pages.Page_Ubicacion
import com.example.proyecto_dashboard.pages.Page_Ver_Mas
import com.example.proyecto_dashboard.pages.WellnessScreen

@Composable
fun Navigation_Host(
    //Parametro para la navegacion
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        //Ruta de Inicio
        startDestination = MenuItem.Page01.ruta
    ) {
        /**
         * Se definen en los [composable] las rutas utilizadas
         */
        composable(MenuItem.Page01.ruta){
            HomeScreen(navController)
        }
        composable(MenuItem.Page02.ruta){
            Page_Flores()
        }
        composable(MenuItem.Page03.ruta){
            Page_Frutas_Verduras()
        }
        composable(MenuItem.Page04.ruta){
            Page_Huevos()
        }
        composable(MenuItem.Page05.ruta){
            Page_Lacteos()
        }
        composable(MenuItem.Page06.ruta){
            Page_Ver_Mas()
        }
        composable(Items_Bar.Boton1.ruta){
            Page_Inicio(Modifier.fillMaxWidth())
        }
        composable(Items_Bar.Boton2.ruta){
            WellnessScreen()
        }
        composable(Items_Bar.Boton3.ruta){
            Page_Informacion()
        }
        composable(Items_Bar.Boton4.ruta){
            Page_Ubicacion()
        }
    }
}

@Composable
fun Current_Route(navController: NavHostController): String? {
    /**
     * Se obtiene el elemento actual de la pila
     * de navegación utilizando el método [currentBackStackEntryAsState]
     */
    val  navBackStackEntry by navController.currentBackStackEntryAsState()
    // Se devuelve la ruta del destino actual, si existe
    return navBackStackEntry?.destination?.route
}