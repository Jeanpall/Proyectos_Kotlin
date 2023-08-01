package com.example.proyecto_dashboard.pages

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Inicio(modifier: Modifier = Modifier) {
    val shouldShowOnboarding by rememberSaveable{ mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            //Llama a la funcion
            Greetings()
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    /*Lista de imagenes con texto*/
    cardCollection: List<DrawableStringPairInicio> = listOf(
        Triple(R.drawable.lechuga,R.string.dia_campesino,R.string.dia_campesino_descripcion),
        Triple(R.drawable.lechuga, R.string.feria_empresarial, R.string.feria_empresarial_descripcion),
        Triple(R.drawable.lechuga, R.string.bicicleta, R.string.bicicleta_descripcion),
        Triple(R.drawable.lechuga, R.string.salud_oral, R.string.salud_oral_descripcion),
        Triple(R.drawable.lechuga, R.string.practicas_laborales, R.string.practicas_laborales_descripcion),
    ).map { DrawableStringPairInicio(it.first, it.second, it.third) }
) {
    // Para cada elemento en la lista cardCollection, llama a la función Greeting con la tarjeta como parámetro
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = cardCollection) { card ->
            Greeting(card = card)
        }
    }
}


@Composable
private fun Greeting(
    card: DrawableStringPairInicio,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        //Llama a la funcion y es donde delegamos el contenido
        CardContent(card)
    }
}

@Composable
private fun CardContent(
    card: DrawableStringPairInicio,
) {
    // Obtiene el contexto actual
    val context = LocalContext.current
    // Esta variable determina si se debe mostrar el contenido expandido de la tarjeta
    var expanded by remember { mutableStateOf(false) }

    // Esto crea una fila con relleno y animación de cambio de tamaño
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "CBA informa sobre:", style = MaterialTheme.typography.titleLarge)
            Text(
                text = stringResource(card.text), style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            //Muestra el contenido expandido de la card
            if (expanded) {
                Text(
                    text = stringResource(card.text2),
                    style = MaterialTheme.typography.headlineSmall
                )
                // Crea un AnnotatedString con un enlace a un sitio web
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 20.sp)
                    ) {
                    append("Haz clic aquí para visitar nuestro Blog")
                }
                    addStringAnnotation(
                        tag = "URL",
                        annotation = "http://bienestarcba.blogspot.com",
                        start = 0,
                        end = this.length
                    )
                }
                // Muestra el AnnotatedString como texto clickable y abre el enlace en el navegador al hacer clic en él
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                context.startActivity(intent)
                            }
                    }
                )
            }
        }
        //Boton que al hacer click despliega o expande el contenido
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
}


private data class DrawableStringPairInicio(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int
)

@Preview
@Composable
fun GretingsPreview() {
    Proyecto_DashboardTheme {
        Greetings()
    }

}