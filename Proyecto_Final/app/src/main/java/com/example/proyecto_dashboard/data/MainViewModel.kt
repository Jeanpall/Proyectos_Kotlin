package com.example.proyecto_dashboard.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Definimos una clase [MainViewModel] que le hereda a [ViewModel]
 */
class MainViewModel : ViewModel() {

    //Almacena el estado de la pantalla
    val screenState = mutableStateOf<UiState>(UiState.Home)

    // Propiedad para almacenar un flujo compartido mutable de valores booleanos
    val cartFlow = MutableSharedFlow<Boolean>()

    //Clase sellada
    sealed class UiState {
        // Subclase Details que toma un parámetro carouselDataModel de tipo CarouselDataModel
        class Details(val carouselDataModel: CarouselDataModel) : UiState()
        object Home : UiState() //Estado pantalla inicio
    }

    //Boton de retroceso
    fun onBackClick() {
        screenState.value = UiState.Home
    }
}

