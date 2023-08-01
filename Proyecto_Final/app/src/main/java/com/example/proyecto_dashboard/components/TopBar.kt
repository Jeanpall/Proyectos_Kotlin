package com.example.proyecto_dashboard.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto_dashboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    //Parametros
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItem: List<MenuItem>
) {
    //Variable para mostrar/ocultar el menu
    var showMenu by remember{
        mutableStateOf(false)
    }
    var  currentRoute = Current_Route(navController = navController)
    var myTytle = "Tienda Sena CBA"
    //Recorre en la lista
    menuItem.forEach { item ->
        if (currentRoute == item.ruta) myTytle = item.title //Actualiza el titulo dependiendo de la ruta
    }
    val idNotification: Int = 0 //Identificador notificación
    val context: Context = LocalContext.current //Contexto actual
    val idChannel: String = stringResource(R.string.canal_tienda) //Identificador del canal
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }
    //Barra superior de la aplicación
    TopAppBar (
        title = { Text(text = myTytle) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                    scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono de Menu"
                )
            }
        },
        //Acciones para los botones parte superior
        actions = {
            IconButton(
                onClick = {
                    notificacionProgramada(
                        context,
                    )
                }
            ) {
                Icon(imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refrescar"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Settings,
                    contentDescription = "Configurar"
                )
            }
            /**
             * [onClick] que despliega un [DropdownMenu]
             */
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Mas Opciones"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.width(150.dp)
            ) {
               DropdownMenuItem(onClick = { /*TODO*/ }) {
                   Icon(
                       imageVector = Icons.Filled.Person,
                       contentDescription = "Idioma"
                   )
                   Spacer(modifier = Modifier.width(10.dp))
                   Text("Idioma")
               }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Compartir")
                }
            }
        }
    )
}
