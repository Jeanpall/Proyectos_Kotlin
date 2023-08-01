package com.example.compose_login.components

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
import androidx.navigation.compose.rememberNavController
import com.example.compose_login.pages.login.LoginScreen

enum class PageScreen(){
    Start,
    DashBoard
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()

    Scaffold() { padding -> ScaffoldContent(
        padding = padding)
        NavHost(
            navController = navController,
            startDestination = PageScreen.Start.name
        ){
            composable(route = PageScreen.Start.name){
                LoginScreen()
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