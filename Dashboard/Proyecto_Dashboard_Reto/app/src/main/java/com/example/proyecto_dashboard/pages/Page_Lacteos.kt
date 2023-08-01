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
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R

@Composable
fun Page_Lacteos(modifier: Modifier = Modifier) {
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
    Triple(R.drawable.la_leche_vaca, R.string.la_leche_vaca, R.string.la_leche_vaca_descripcion),
    Triple(R.drawable.la_leche_cabra, R.string.la_leche_cabra, R.string.la_leche_cabra_descripcion),
    Triple(R.drawable.la_yogurt, R.string.la_yogurt, R.string.la_yogurt_descripcion),
    Triple(R.drawable.la_queso, R.string.la_queso, R.string.la_queso_descripcion),
).map { DrawableStringPairLacteos(it.first, it.second, it.third) }


private data class DrawableStringPairLacteos(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)

