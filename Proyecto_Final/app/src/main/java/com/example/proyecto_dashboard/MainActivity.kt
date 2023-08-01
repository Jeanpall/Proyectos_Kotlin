package com.example.proyecto_dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_dashboard.components.TiendaApp
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_DashboardTheme {
                val navController = rememberNavController()
                // Llamar a la funcion
                TiendaApp(navController)
            }
        }
    }
}
