package com.example.proyecto_dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.util.Locale

//Lista de imagenes Carrusel
@Composable
fun MyScreen() {
    val images = listOf(
        R.drawable.tulipan,
        R.drawable.ic_frutas_verduras,
        R.drawable.bg_tienda_cba,
    )

    ImageCarousel(images)
}


//Carrusel
@Composable
fun ImageCarousel(
    images: List<Int>
) {
    //Estado que tiene el indice de la imagen
    var image by remember { mutableStateOf(0) }

    //Crea la funcion para cuando el usuario haga el deslice hacia la izquierda
    fun nextIndex() {
        image = (image + 1) % images.size
    }

    //Crea la funcion para retroceder el indice
    fun prevIndex() {
        image = if (image - 1 < 0) images.size - 1 else image - 1
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            count = images.size,
            state = rememberPagerState(image),
            modifier = Modifier
                .width(600.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = { velocity ->
                            if (velocity < 0) {
                                nextIndex()
                            } else {
                                prevIndex()
                            }
                        }
                    )
                }
        ) { page ->
            Image(
                painter = painterResource(images[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private operator fun Any?.compareTo(i: Int): Int {
    TODO("Not yet implemented")
}

fun detectHorizontalDragGestures(onDragEnd: (Any?) -> Unit) {

}

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
        onValueChange = { newValue -> searchQuery = newValue }, /*Actualiza el "Value" asignando el nuevo valor del usuario*/
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
/*Categorias*/
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
) {
    var isHovered by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .scale(if (isHovered) 1.1f else 1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isHovered = true
                        tryAwaitRelease()
                        isHovered = false
                    }
                )
            }
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
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
}


/*Fila con Scroll*/
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
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

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        MyScreen()
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))
        HomeSection(title = R.string.categorias) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.productos) {
            FavoriteCollectionsGrid()
            Spacer(Modifier.padding(vertical = 2.dp))
            HomeSection(title = R.string.destacados) {
                FavoriteCollectionsGrid2()
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

/*Card*/
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(192.dp)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

/*Grid con Scroll*/
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(120.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}

/*Fila N2*/
@Composable
fun FavoriteCollectionsGrid2(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(120.dp)
    ) {
        items(favoriteCollectionsData2) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}


/*Lista Imagenes y Texto*/
private val alignYourBodyData = listOf(
    R.drawable.pr_flores to R.string.pri_flores,
    R.drawable.pr_frutas_verduras to R.string.pri_frutas_verduras,
    R.drawable.pr_huevos to R.string.pri_huevos,
    R.drawable.pr_lacteos to R.string.pri_Lacteos,
    R.drawable.pr_ver_mas to R.string.pri_ver_mas,
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.carne to R.string.Carne,
    R.drawable.lechuga to R.string.Lechuga,
    R.drawable.lacteos to R.string.Leche,
    R.drawable.mora to R.string.Mora,
    R.drawable.manzana to R.string.Manzana,
    R.drawable.fresa to R.string.Fresa,
    R.drawable.pera to R.string.Pera,
    R.drawable.coliflor to R.string.Coliflor,
    R.drawable.cebolla_cabezona to R.string.Cebolla_cabezona,
    R.drawable.banano to R.string.Banano,
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData2 = listOf(
    R.drawable.cebolla_larga to R.string.Cebolla,
    R.drawable.miel to R.string.Miel,
    R.drawable.espinaca to R.string.Espinaca,
    R.drawable.girasol to R.string.Girasol,
    R.drawable.tulipan to R.string.Tulipan,
    R.drawable.naranja to R.string.Naranja,
    R.drawable.mandarina to R.string.Mandarina,
    R.drawable.arveja to R.string.Arveja,
    R.drawable.rosas to R.string.Rosas,
    R.drawable.mazorca to R.string.Mazorca,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)



