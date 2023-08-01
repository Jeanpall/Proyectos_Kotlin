package com.example.proyecto_dashboard.data

import androidx.compose.ui.graphics.Color
import com.example.proyecto_dashboard.R

/**
 * Definimos una clase [CarouselDataModel] el cual tendra nuestros recursos
 * para el contenido del Carrusel
 */
data class CarouselDataModel(
    val resId: Int,
    val title: String,
    val description: String,
    val price: String,
    val color: Color,
    val aboutProduct: String = "Meet the $description TW. Inspired by the treasured franchise that brought $description cushioning to the world and laid the foundation for the aesthetic."
) {
    //Objeto compañero
    companion object {

        // Define una lista mutable de objetos CarouselDataModel
        val listOfShoes = mutableListOf<CarouselDataModel>().apply {
            //Agregar los objetos que queremos en el Carrusel
            add(
                CarouselDataModel(
                    resId = R.drawable.pr_carne,
                    title = "Carne",
                    description = "De Res",
                    price = "$ 15,000",
                    color = Color(0xFFeb4658)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.pr_lechuga,
                    title = "Lechuga",
                    description = "Crespa",
                    price = "$ 10,000",
                    color = Color(0xFF6887cb)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.pr_banano,
                    title = "Banano",
                    description = "Tabasco",
                    price = "$ 3,000",
                    color = Color(0xFFfe7153)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.pr_cebolla_cabezona,
                    title = "Cebolla",
                    description = "Cabezona",
                    price = "$ 6,500",
                    color = Color(0xFF45bfff)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.pri_fresa,
                    title = "Fresa",
                    description = "Conica",
                    price = "$ 2,300",
                    color = Color(0xFFAFCA71)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.pr_leche,
                    title = "Leche",
                    description = "De vaca",
                    price = "$ 12,000",
                    color = Color(0xFFBBA45C)
                )
            )
        }

        //Define los titles del Tab
        val categories = listOf("New", "Featured", "Trending")
    }
}
