package com.example.proyecto_dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Informacion() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = R.drawable.splash_sena_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .align(Alignment.Start),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_info_24),
                contentDescription = null
            )
            Text(
                text = "Ultimo en Noticias",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            // Muestra una lista de elementos indexados, donde cada elemento es una card
            itemsIndexed(Information) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = item.drawable),
                            contentDescription = null,
                            modifier = Modifier
                                .height(170.dp)
                                .size(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = item.text),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = item.text2),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}




/*Lista Imagenes y Texto*/

private val Information = listOf(
    Triple(R.drawable.inf_card_01, R.string.card_01, R.string.card_description_01),
    Triple(R.drawable.inf_card_02, R.string.card_02, R.string.card_description_02),
    Triple(R.drawable.inf_card_03, R.string.card_03, R.string.card_description_03),
    Triple(R.drawable.inf_card_04, R.string.card_04, R.string.card_description_04),
).map { DrawableStringPairInformation(it.first, it.second, it.third) }


private data class DrawableStringPairInformation(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)

@Preview
@Composable
fun Preview_Page_Informacion() {
    Proyecto_DashboardTheme {
        Page_Informacion()
    }
}