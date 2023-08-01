package com.example.proyecto_dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto_dashboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    //Parametros
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menu_items: List<MenuItem>
) {
    Column() {
        Image(
            painterResource(id = R.drawable.bg_tienda_cba),
            contentDescription = "Menu de Opciones",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        // Se obtiene la ruta actual del NavController
        val currentRoute = Current_Route(navController)

        // Se itera sobre cada elemento de la lista menu_items
        menu_items.forEach{ item ->
            DrawerItem(
                item = item,
                // El ítem está seleccionado si su ruta coincide con la ruta actual
                selected = currentRoute == item.ruta
            ) {
                // Al hacer clic en el ítem, se navega a su ruta utilizando el NavController
                navController.navigate(item.ruta){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun DrawerItem (
    //Parametros
    item: MenuItem,
    selected: Boolean,
    onItemClick: (MenuItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(16))
            .background(
                if (selected) MaterialTheme.colors.primaryVariant.copy(alpha = 0.25f)
                else Color.Transparent
            )
            .padding(8.dp)
                /**
                 * Al hacer clic en el contenedor, se ejecuta la función
                 * lambda [onItemClick] pasando el objeto item como argumento
                 */
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = item.icon),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.body1,
            color = if (selected) MaterialTheme.colors.secondaryVariant
            else MaterialTheme.colors.onBackground
        )
    }
}