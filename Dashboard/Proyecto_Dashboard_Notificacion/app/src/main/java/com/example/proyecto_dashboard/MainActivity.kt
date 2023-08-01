package com.example.proyecto_dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyecto_dashboard.components.MainPage
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_DashboardTheme {
                // A surface container using the 'background' color from the theme
                MainPage()
            }
        }
    }
}
