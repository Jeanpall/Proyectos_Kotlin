package com.example.proyecto_dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.MenuItem
import com.example.proyecto_dashboard.data.CarouselDataModel
import com.example.proyecto_dashboard.data.MainViewModel
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme
import com.example.proyecto_dashboard.ui.theme.textColor
import com.example.proyecto_dashboard.utils.Utils
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.absoluteValue

/* Barra de Busqueda */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    /*Variable que inicializa una cadena vacia*/
    var searchQuery by remember { mutableStateOf("") }

    TextField(
        value = searchQuery,
        /*Actualiza el "Value" asignando el nuevo valor del usuario*/
        onValueChange = { newValue -> searchQuery = newValue },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 54.dp)
    )
}

@Composable
fun CarouselProduct(viewModel: MainViewModel) {
    // Crea un estado para el carrusel
    val pagerState = rememberPagerState()
    //Variable que almacena las categorias
    val selectedCategory = remember { mutableStateOf(CarouselDataModel.categories.size - 1) }
    //Lanza coroutines
    val rememberScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.width(64.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Muestra cada categoría en la columna
            CarouselDataModel.categories.forEachIndexed { index, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .height(90.dp)
                        .graphicsLayer {
                            rotationZ = -90f
                            translationX = 100f
                        }
                            //Información al hacer click en el Carousel
                        .clickable {
                            selectedCategory.value = index
                            rememberScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedCategory.value == index) textColor else Color.LightGray,
                    maxLines = 1,
                )
            }
        }
        // Crea un carrusel horizontal
        HorizontalPager(
            count = CarouselDataModel.listOfShoes.size,
            contentPadding = PaddingValues(end = 70.dp),
            state = pagerState
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            // Muestra el elemento en la página actual del carrusel
            ShoeItem(shoe = CarouselDataModel.listOfShoes[page], pageOffset, viewModel)
        }
    }
}

@Composable
fun ShoeItem(shoe: CarouselDataModel, pageOffset: Float, viewModel: MainViewModel) {
    //Calcula la escala del elemento
    val scale = Utils.lerp(
        start = 0.5f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    //Calcula el angulo
    val angle = Utils.lerp(
        start = 30f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    //Calcula las escalas en X/Y
    val scaleXBox = Utils.lerp(
        start = 0.9f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleYBox = Utils.lerp(
        start = 0.7f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val rotateY = Utils.lerp(
        start = 10f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    //Animaciones
    val boxAngle: Float by animateFloatAsState(
        targetValue = rotateY,

        animationSpec = tween(durationMillis = 600, easing = Utils.EaseOutQuart)
    )
    val boxScaleX: Float by animateFloatAsState(
        targetValue = scaleXBox,

        animationSpec = tween(durationMillis = 800, easing = Utils.EaseOutQuart)
    )
    val boxScaleY: Float by animateFloatAsState(
        targetValue = scaleYBox,

        animationSpec = tween(durationMillis = 800, easing = Utils.EaseOutQuart)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,

        animationSpec = tween(durationMillis = 600, easing = Utils.EaseOutQuart)
    )
    val visibility = Utils.lerp(
        start = 0f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )

    // Crea una caja clickable que cambia el estado de la pantalla al hacer clic en ella
    Box(modifier = Modifier.clickable {
        viewModel.screenState.value = MainViewModel.UiState.Details(shoe)
    }) {
        // Crea una caja con una capa gráfica que anima la escala y rotación
        Box(
            modifier = Modifier
                .graphicsLayer {
                    Utils
                        .lerp(
                            start = 0.90f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also {
                            scaleX = boxScaleX
                            scaleY = boxScaleY
                            rotationY = boxAngle
                        }
                }
                .height(280.dp)
                .width(210.dp)
                .background(color = shoe.color.copy(alpha = .8f), RoundedCornerShape(20.dp))
                .padding(end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .alpha(visibility)
            ) {
                // Crea una columna para mostrar el título, descripción y precio
                Column {
                    Text(
                        text = shoe.title,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = shoe.description,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = shoe.price,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = .9f),
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "like",
                    colorFilter = ColorFilter.tint(Color.White),
                )
            }
            Image(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "go to next",
                colorFilter = ColorFilter.tint(Color.White),
            )
        }
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(220.dp)
        ) {

            Image(
                painter = painterResource(id = shoe.resId),
                contentDescription = "",
                modifier = Modifier
                    .height(340.dp)
                    .align(
                        Alignment.Center
                    )
                    .rotate(330f)
                    .offset(x = 20.dp, y = 10.dp)
                    .size(320.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        rotationZ = imageAngle
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}

/*Categorias*/
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    navController: NavHostController,
    index: Int
) {
    //determinar la ruta de navegación correcta en función del índice del elemento en la lista.
    val route = when (index) {
        0 -> MenuItem.Page02.ruta
        1 -> MenuItem.Page03.ruta
        2 -> MenuItem.Page04.ruta
        3 -> MenuItem.Page05.ruta
        4 -> MenuItem.Page06.ruta
        else -> "rutaPorDefecto"
    }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clickable { navController.navigate(route) }
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.paddingFromBaseline(
                    top = 24.dp, bottom = 8.dp
                ),
                textAlign = TextAlign.Center
            )
        }
    }



/*Fila con Scroll*/
/**
 * la Funcion [itemsIndexed] itera sobre los elementos de la lista
 * pasa el indice como el elemento a la funcion [AlignYourBodyElement]
 */
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 23.dp),
        modifier = modifier
    ) {
        itemsIndexed(alignYourBodyData) { index, item ->
            AlignYourBodyElement(item.drawable, item.text, navController = navController, index = index)
        }
    }
}

/*Titulo para las categorias*/
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

/**
 * Muestra todos los componentes en la pantalla, para eso es el [HomeScreen]
 */
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp)
    ) {
        Spacer(Modifier.height(5.dp))
        CarouselProduct(viewModel())
        //SearchBar(Modifier.padding(horizontal = 16.dp))

        Spacer(Modifier.height(5.dp))
        AlignYourBodyRow(navController = navController)

        HomeSection(title = R.string.productos) {
            FavoriteCollectionsGrid()
            Spacer(Modifier.padding(vertical = 2.dp))
        }
        Spacer(Modifier.height(20.dp))
    }
}

/*Card*/
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    @StringRes text2: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .padding(start = 16.dp, end = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(drawable),
                    contentDescription = "shoe",
                    modifier = Modifier
                        .padding(8.dp)
                        .height(90.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(text),
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(text2),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h3
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "fav",
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.favorite_mark_24),
                contentDescription = "fav",
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.TopStart)
                    .offset(x = 4.dp, y = (-4).dp)
                    .clip(RoundedCornerShape(8.dp)),
                tint = Color.Red
            )
        }
    }
}

/*Grid con Scroll*/
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Crea una cuadrícula horizontal con una fila
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(200.dp)
    ) {
        // Muestra cada elemento de la lista de colecciones
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                text2 = item.text2,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}



/*Lista Imagenes y Texto*/
private val alignYourBodyData = listOf(
    R.drawable.flores to R.string.pri_flores,
    R.drawable.frutas to R.string.pri_frutas_verduras,
    R.drawable.huevos to R.string.pri_huevos,
    R.drawable.lacteos to R.string.pri_Lacteos,
    R.drawable.pr_more to R.string.pri_ver_mas,
).map { DrawableStringPair(it.first, it.second) }


private val favoriteCollectionsData = listOf(
    Triple(R.drawable.pr_carne, R.string.pri_carne, R.string.pri_carne_precio),
    Triple(R.drawable.pr_lechuga, R.string.pri_lechuga, R.string.pri_lechuga_precio),
    Triple(R.drawable.pr_cebolla_cabezona, R.string.pri_cebolla_cabezona, R.string.pri_cebolla_cabezona_precio),
    Triple(R.drawable.pr_banano, R.string.pri_banano, R.string.pri_banano_precio),
).map { DrawableStringPair2(it.first, it.second, it.third) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private data class DrawableStringPair2(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)

@Preview
@Composable
fun Favoritecollection() {
    Proyecto_DashboardTheme() {
        FavoriteCollectionsGrid()
    }
}



