package com.example.proyecto_dashboard.pages

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Ver_Mas(modifier: Modifier = Modifier) {
    //Variable que permite recordar el estado para el cambio de color,
    // Ademas para que cada uno sea independiente en su estado.
    val botonList = remember {
        mutableStateMapOf<Int, Boolean>().apply {
            favoriteCollectionsData.forEach { card ->
                put((card.text), false)
            }
        }
    }

    LazyColumn(modifier = modifier) {
        // Muestra una lista de elementos, donde cada elemento es una fila de card
        items(favoriteCollectionsData.chunked(2)) { row ->
            Row {
                // Para cada card en la fila crear una componible
                row.forEachIndexed {index, card ->
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                            Column() {
                                Text(
                                    text = stringResource(card.text2),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.Red
                                    ),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            Row(
                                modifier = Modifier,
                            ) {
                                IconButton(
                                    // Click que permite leer la lista y que cada boton tenga su propio estado
                                    onClick = { botonList[(card.text)] = !botonList[(card.text)]!! }
                                ) {
                                    Icon(
                                        modifier = Modifier,
                                        painter = painterResource(id = R.drawable.ic_heart),
                                        contentDescription = "Me Gusta",
                                        tint = if (botonList[card.text]!!) Color.Red else Color.Unspecified
                                    )
                                }
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        modifier = Modifier,
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "Compartir"
                                    )
                                }
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        modifier = Modifier,
                                        imageVector = Icons.Filled.AddShoppingCart,
                                        contentDescription = null
                                    )
                                }
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
    Triple(R.drawable.ver_pimienta, R.string.ver_pimienta, R.string.ver_pimienta_precio),
    Triple(R.drawable.ver_canela, R.string.ver_canela, R.string.ver_canela_precio),
    Triple(R.drawable.ver_comino, R.string.ver_comino, R.string.ver_comino_precio),
    Triple(R.drawable.ver_miel, R.string.ver_miel, R.string.ver_miel_precio),
).map { DrawableStringPairVerMas(it.first, it.second, it.third) }


private data class DrawableStringPairVerMas(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)

@Preview
@Composable
fun Ver_mas_preview() {
    Proyecto_DashboardTheme {
        Page_Ver_Mas()
    }
}