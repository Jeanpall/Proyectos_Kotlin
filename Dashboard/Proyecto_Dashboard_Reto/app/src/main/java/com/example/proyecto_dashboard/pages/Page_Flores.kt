package com.example.proyecto_dashboard.pages

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.notificacionSencilla
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Flores(modifier: Modifier = Modifier) {

    //Variable que permite recordar el estado para el cambio de color,
    // Ademas para que cada uno sea independiente en su estado.
    val botonList = remember { mutableStateListOf<Boolean>().apply { addAll(List(favoriteCollectionsData.size) { false }) } }

    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort: String = "Le has dado Me gusta al Producto"
    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    LazyColumn(modifier = modifier) {
        items(favoriteCollectionsData.chunked(2)) { row ->
            Row {
                row.forEachIndexed { index, card ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(card.drawable),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(200.dp)
                            )
                            Row(
                                modifier =  Modifier,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = stringResource(card.text),
                                    style = MaterialTheme.typography.h1.copy(
                                        fontWeight = FontWeight.ExtraBold,

                                    ),
                                    modifier = Modifier.padding(8.dp),
                                )
                            }
                            Row {
                                IconButton(
                                    // Click que permite leer la lista y que cada boton tenga su propio estado
                                    onClick = { botonList[index] = !botonList[index] }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(10.dp),
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "Me Gusta",
                                        tint = if (botonList[index]) Color.Yellow else Color.Gray
                                    )
                                }
                                IconButton(
                                    onClick = {
                                    notificacionSencilla(
                                        context,
                                        idChannel,
                                        idNotification,
                                        "Notificacion Sencilla",
                                        textShort
                                    ) }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(10.dp),
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "Compartir"
                                    )
                                }
                            }
                            Column() {
                                Text(
                                    text = stringResource(card.text2),
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            Row(
                                modifier = Modifier,
                            ) {
                                Button(
                                    modifier = Modifier.padding(10.dp),
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(Color(0xFF8BC34A)),
                                ) {
                                    Text(
                                        "Detalles"
                                    )
                                }
                                Icon(
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .size(25.dp),
                                    imageVector = Icons.Filled.AddShoppingCart,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}



/*Lista Imagenes y Texto*/

private val favoriteCollectionsData = listOf(
    Triple(R.drawable.tulipan, R.string.Tulipan, R.string.Tulipan_descripcion),
    Triple(R.drawable.rosas, R.string.Rosas, R.string.Rosas_descripcion),
    Triple(R.drawable.girasol, R.string.Girasol, R.string.Girasol_descripcion),
    Triple(R.drawable.fl_amapola, R.string.Amapola, R.string.Amapola_descripcion),
    Triple(R.drawable.fl_margarita, R.string.Margarita, R.string.Margarita_descripcion),
    Triple(R.drawable.fl_clavel, R.string.Clavel, R.string.Clavel_descripcion),
).map { DrawableStringPairFlores(it.first, it.second, it.third) }


private data class DrawableStringPairFlores(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)



@Preview
@Composable
fun HomeScreenFloresPreview() {
    Proyecto_DashboardTheme() {
        Page_Flores()
    }
}