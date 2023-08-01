package com.example.proyecto_dashboard.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.notificacionExtensa
import com.example.proyecto_dashboard.components.notificacionImagen
import com.example.proyecto_dashboard.components.notificacionProgramada
import com.example.proyecto_dashboard.components.notificacionSencilla

@Composable
fun Page_Informacion() {

    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort: String = "Ejemplo de Notificacion sencilla con prioridad por omision (default)"
    val textLong: String = "Saludos! Esta es una prueba de notificaciones. Debe aparecer " +
            "un icono a la derecha y el texto puede tener una longitud de 200 caracteres. " +
            "El tamaño de la notificacion puede colapsar y/o expandirse." +
            "Gracias y hasta pronto"

    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_shopping_cart_24
    )
    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    val modifier = Modifier
        .padding(18.dp)
        .fillMaxWidth()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Informacion de notificaciones",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 100.dp)
        )
        Button(
            onClick = {
                notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Notificacion Sencilla",
                    textShort
                )
            },
            modifier = modifier
        ) {
            Text(
                text = "Notificacion Sencilla"
            )
        }
        Button(
            onClick = {
                notificacionExtensa(
                    context,
                    idChannel,
                    idNotification + 1,
                    "Notificacion Extensa",
                    textLong,
                )
            },
            modifier = modifier
        ) {
            Text(
                text = "Notificacion Extensa"
            )
        }
        Button(
            onClick = {
                notificacionImagen(
                    context,
                    idChannel,
                    idNotification + 2,
                    "Notificacion con Imagen",
                    textLong,
                    imagenBig
                )
            },
            modifier = modifier
        ) {
            Text(
                text = "Notificacion con Imagen"
            )
        }
        Button(
            onClick = {
                notificacionProgramada(
                    context
                )
            },
            modifier = modifier
        ) {
            Text(
                text = "Notificacion Programada"
            )
        }
    }
}