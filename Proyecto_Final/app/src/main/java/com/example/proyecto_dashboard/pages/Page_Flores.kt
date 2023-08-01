package com.example.proyecto_dashboard.pages

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.sp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.notificacionSencilla
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Page_Flores(modifier: Modifier = Modifier) {

    //Variable que permite recordar el estado para el cambio de color,
    // Ademas para que cada uno sea independiente en su estado.
    val botonList = remember {
        mutableStateMapOf<Int, Boolean>().apply {
            favoriteCollectionsData.forEach { card ->
                put((card.text), false)
            }
        }
    }

    val idNotification: Int = 0 //Identidicador de notificación
    val context: Context = LocalContext.current //Contexto actual
    val idChannel: String = stringResource(R.string.canal_tienda) //Identificador del canal
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort = "Usted a compartido el Producto"
    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    // Variable que almacena el producto y que el valor inicial es null
    val selectedItem = remember { mutableStateOf<DrawableStringPairFlores?>(null) }

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
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
                                        //Al darle click genera la notificación
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

/*Lista privada Imagenes y Texto*/

private val favoriteCollectionsData = listOf(
    DrawableStringPairFlores(R.drawable.tulipan, R.string.Tulipan, R.string.Tulipan_descripcion, R.string.precio_tulipan, R.string.medida_tulipan),
    DrawableStringPairFlores(R.drawable.rosas, R.string.Rosas, R.string.Rosas_descripcion, R.string.precio_rosas, R.string.medida_rosas),
    DrawableStringPairFlores(R.drawable.girasol, R.string.Girasol, R.string.Girasol_descripcion, R.string.precio_girasol, R.string.medida_girasol),
    DrawableStringPairFlores(R.drawable.fl_amapola, R.string.Amapola, R.string.Amapola_descripcion, R.string.precio_amapola, R.string.medida_amapola),
    DrawableStringPairFlores(R.drawable.fl_margarita, R.string.Margarita, R.string.Margarita_descripcion, R.string.precio_margarita, R.string.medida_margarita),
    DrawableStringPairFlores(R.drawable.fl_clavel, R.string.Clavel, R.string.Clavel_descripcion, R.string.precio_clavel, R.string.medida_clavel)
)

private data class DrawableStringPairFlores(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int,
    @StringRes val precio: Int,
    @StringRes val medida: Int
)





@Preview
@Composable
fun HomeScreenFloresPreview() {
    Proyecto_DashboardTheme() {
        Page_Flores()
    }
}