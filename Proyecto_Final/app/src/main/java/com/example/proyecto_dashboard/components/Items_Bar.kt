package com.example.proyecto_dashboard.components

import com.example.proyecto_dashboard.R

/**
 * Se define una clase sellada llamada [Items_Bar]
 * que contiene tres propiedades, Icon, Title y Ruta.
 */
sealed class Items_Bar(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object  Boton1: Items_Bar(R.drawable.ic_bike_24, "Inicio", "boton1")
    object  Boton2: Items_Bar(R.drawable.ic_moto_24, "Contenido", "boton2")
    object  Boton3: Items_Bar(R.drawable.ic_recycling_24, "Información", "boton3")
    object  Boton4: Items_Bar(R.drawable.ic_location_24, "Ubicacion", "boton4")
}
