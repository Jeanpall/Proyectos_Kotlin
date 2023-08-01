package com.example.proyecto_dashboard.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto_dashboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomMenu(
    navController: NavHostController,
    menu_items_bar: List<Items_Bar> //Llama a la lista
) {
    // Se utiliza el componente BottomAppBar para construir la barra inferior
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
    ) {
        // Se utiliza el componente BottomNavigation para construir
        // el menú de navegación de la barra inferior.
        BottomNavigation(
            modifier = Modifier
                .padding(
                    0.dp,
                    0.dp,
                    60.dp,
                    0.dp
                )
        ) {
            // Se obtiene la ruta actual del NavController
            val currentRouteBar = Current_Route(navController = navController)

            // Se itera sobre cada elemento de la lista menu_items_bar
            menu_items_bar.forEach { item ->
                BottomNavigationItem(
                    selected = currentRouteBar == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title, fontSize = 8.sp) }
                )
            }
        }
    }
}

@Composable
fun Fab(
    //Parametros
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    //Identificador de la notificacion
    val idNotification: Int = 0

    // Contexto actual
    val context: Context = LocalContext.current

    // Identificador del canal de notificaciones
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort: String = "Ejemplo de Notificacion sencilla con prioridad por omision (default)"
    val textLong: String = "Saludos! Esta es una prueba de notificaciones. Debe aparecer " +
            "un icono a la derecha y el texto puede tener una longitud de 200 caracteres. " +
            "El tamaño de la notificacion puede colapsar y/o expandirse." +
            "Gracias y hasta pronto"

    //El icono debe ser una imagen
    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_shopping_cart_24
    )
    // Imagen para la Notificacion Extensa
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

    FloatingActionButton(
        onClick = {
            //Despliega la Notificacion
            notificacionExtensa(
                context,
                idChannel,
                idNotification + 1,
                "Notificacion Extensa",
                textLong,
            )
            /**
             * Se lanza una corrutina utilizando el objeto [scope]
             * Se muestra un mensaje utilizando el componente [Snackbar]
             */
            scope.launch {
                scaffoldState.snackbarHostState
                    .showSnackbar(
                        "Proximamente Disponible!",
                        actionLabel = "Aceptar",
                        duration = SnackbarDuration.Indefinite
                    )
            }
        },
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "Recompensas"
        )
    }
}
