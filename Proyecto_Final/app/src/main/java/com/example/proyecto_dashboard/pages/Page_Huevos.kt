package com.example.proyecto_dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Page_Huevos(modifier: Modifier = Modifier) {
    //Variable que permite recordar el estado para el cambio de color,
    // Ademas para que cada uno sea independiente en su estado.
    val botonList = remember {
        mutableStateMapOf<Int, Boolean>().apply {
            favoriteCollectionsData.forEach { card ->
                put((card.text), false)
            }
        }
    }

    // Variable que almacena el producto y que el valor inicial es null
    val selectedItem = remember { mutableStateOf<DrawableStringPairHuevos?>(null) }

    // Crea una instancia de BottomSheetScaffoldState para controlar el estado de la hoja inferior
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    // Crea una instancia de CoroutineScope para lanzar corrutinas dentro del ámbito de la composición
    val scope = rememberCoroutineScope()

    // Define un BottomSheetScaffold y establece sus propiedades
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            /**
             * Verifica si hay un elemento seleccionado y
             * Muestra la imagen y textos correspondientes
             */
            selectedItem.value?.let { item ->
                Image(
                    painter = painterResource(item.drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(item.text),
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    modifier = Modifier.padding(5.dp),
                )
                Row() {
                    Text(
                        text = stringResource(item.precio),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(5.dp),
                    )
                    Text(
                        text = stringResource(item.medida),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(5.dp),
                    )
                }
                Text(
                    text = stringResource(item.text2),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp),
                )
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    ) {
        LazyColumn(modifier = modifier) {
            // Muestra una lista de elementos, donde cada elemento es una fila de tarjetas
            items(favoriteCollectionsData.chunked(2)) { row ->
                Row {
                    // Para cada card en la fila crear una componible
                    row.forEachIndexed { index, card ->
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                // Establece el valor del elemento seleccionado en la card actual
                                .clickable {
                                    selectedItem.value = card
                                    //Lanza la corrutina
                                    scope.launch {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    }
                                }
                        )  {
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
                                Row() {
                                    Text(
                                        text = stringResource(card.precio),
                                        style = MaterialTheme.typography.h5.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Red
                                        ),
                                        modifier = Modifier.padding(6.dp),
                                    )
                                }
                                Row {
                                    IconButton(
                                        // Click que permite leer la lista y que cada boton tenga su propio estado
                                        onClick = { botonList[(card.text)] = !botonList[(card.text)]!! }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .size(28.dp),
                                            painter = painterResource(id = R.drawable.ic_heart),
                                            contentDescription = "Me Gusta",
                                            tint = if (botonList[card.text]!!) Color.Red else Color.Unspecified
                                        )
                                    }
                                    IconButton(
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(10.dp),
                                            imageVector = Icons.Filled.Share,
                                            contentDescription = "Compartir"
                                        )
                                    }
                                    IconButton(
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(10.dp),
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
}



/*Lista Imagenes y Texto*/

private val favoriteCollectionsData = listOf(
    DrawableStringPairHuevos(R.drawable.hu_gallina, R.string.hu_huevos_gallina, R.string.hu_huevos_gallina_descripcion, R.string.precio_huevos_gallina, R.string.medida_huevos_gallina),
    DrawableStringPairHuevos(R.drawable.hu_codorniz, R.string.hu_huevos_codorniz, R.string.hu_huevos_codorniz_descripcion, R.string.precio_huevos_codorniz, R.string.medida_huevos_codorniz),
    DrawableStringPairHuevos(R.drawable.hu_avestruz, R.string.hu_huevos_avestruz, R.string.hu_huevos_avestruz_descripcion, R.string.precio_huevos_avestruz, R.string.medida_huevos_avestruz),
    DrawableStringPairHuevos(R.drawable.hu_jumbo, R.string.hu_huevos_jumbo, R.string.hu_huevos_jumbo_descripcion, R.string.precio_huevos_jumbo, R.string.medida_huevos_jumbo),
)


private data class DrawableStringPairHuevos(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int,
    @StringRes val precio: Int,
    @StringRes val medida: Int
)

@Preview
@Composable
fun Preview_page() {
    Proyecto_DashboardTheme() {
        Page_Huevos()
    }
}