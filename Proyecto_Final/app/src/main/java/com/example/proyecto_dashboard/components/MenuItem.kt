package com.example.proyecto_dashboard.components

import com.example.proyecto_dashboard.R

/**
 * Se define una clase sellada llamada [MenuItem]
 * que contiene tres propiedades, Icon, Title y Ruta.
 */
sealed class MenuItem (
    val icon: Int,
    val title: String,
    val ruta: String
    ) {
    object Page01: MenuItem(R.drawable.ic_principal, "Principal", "page01")
    object Main: MenuItem(R.drawable.ic_principal, "main", "main")
    object Page02: MenuItem(R.drawable.ic_flores, "Flores", "page02")
    object Page03: MenuItem(R.drawable.ic_frutas_verduras, "Frutas y verduras", "page03")
    object Page04: MenuItem(R.drawable.ic_huevos, "Huevos", "page04")
    object Page05: MenuItem(R.drawable.ic_lacteos, "Lacteos", "page05")
    object Page06: MenuItem(R.drawable.ic_ver_mas, "Ver Mas", "page06")
}
