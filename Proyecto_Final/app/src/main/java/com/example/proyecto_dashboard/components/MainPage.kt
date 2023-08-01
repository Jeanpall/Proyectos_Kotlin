package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun MainPage(){
    //Navegacion entre pantallas
    val navController = rememberNavController()

    // scaffoldState: guarda el estado permitiendo conocer la configuracion
    val scaffoldState = rememberScaffoldState()

    // scope: Utilizada para abrir y cerrar el menu lateral
    val scope = rememberCoroutineScope()

    //Opciones de navegacion del drawer
    val navigationItems = listOf(
        MenuItem.Page01,
        MenuItem.Page02,
        MenuItem.Page03,
        MenuItem.Page04,
        MenuItem.Page05,
        MenuItem.Page06,
    )
    // Opciones de navegacion del BottomBar
    val navigationItemsBottomBar = listOf(
        Items_Bar.Boton1,
        Items_Bar.Boton2,
        Items_Bar.Boton3,
        Items_Bar.Boton4,
    )

    /**
     * [Scaffold] estructura basica de la pantalla el cual pasa unos parametros
     * y llama al [TopBar], [DrawerMenu], [BottomMenu] y [floatingActionButton]
     */
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope,
                scaffoldState,
                navController,
                navigationItems
            )
        },
        drawerContent = {
            DrawerMenu(
                scope,
                scaffoldState,
                navController,
                menu_items = navigationItems
            )
        },
        bottomBar = {
            BottomMenu(
                navController,
                menu_items_bar = navigationItemsBottomBar
            )
        },
        floatingActionButton = {
            Fab(
                scope,
                scaffoldState
            )
        },
        isFloatingActionButtonDocked = true
    )
    { padding ->
        ContentScaffold(
            padding = padding
        )
        // Se define el host de navegación utilizando el objeto navController
        Navigation_Host(navController)
    }
}
@Composable
fun ContentScaffold(padding: PaddingValues){

}

@Preview
@Composable
fun MainPagePreview() {
    Proyecto_DashboardTheme {
        // A surface container using the 'background' color from the theme
        MainPage()
    }
}