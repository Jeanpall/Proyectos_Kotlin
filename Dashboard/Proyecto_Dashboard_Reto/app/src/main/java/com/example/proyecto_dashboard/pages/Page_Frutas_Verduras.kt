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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
fun Page_Frutas_Verduras(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(favoriteCollectionsData.chunked(2)) { row ->
            Row {
                row.forEach { card ->
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
                                Icon(
                                    modifier = Modifier.padding(10.dp),
                                    painter = painterResource(R.drawable.sentiment_satisfied_24),
                                    contentDescription = "Me Gusta"
                                )
                                Icon(
                                    modifier = Modifier.padding(10.dp),
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "Compartir"
                                )
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
                                    colors = ButtonDefaults.buttonColors(Color(0xFF8BC34A))
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
    Triple(R.drawable.arveja, R.string.Arveja, R.string.Arveja_descripcion),
    Triple(R.drawable.mazorca, R.string.Mazorca, R.string.Mazorca_descripcion),
    Triple(R.drawable.zanahoria, R.string.Zanahoria, R.string.Zanahoria_descripcion),
    Triple(R.drawable.fresa, R.string.Fresa, R.string.Fresa_descripcion),
    Triple(R.drawable.manzana, R.string.Manzana, R.string.Manzana_descripcion),
    Triple(R.drawable.mora, R.string.Mora, R.string.Mora_descripcion),
).map { DrawableStringPairFrutas_verduras(it.first, it.second, it.third) }


private data class DrawableStringPairFrutas_verduras(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)



@Preview
@Composable
fun HomeScreenFrutasPreview() {
    Proyecto_DashboardTheme() {
        Page_Frutas_Verduras()
    }
}