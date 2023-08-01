package com.example.proyecto_dashboard.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Contenidos(
    count: Int, //Parametro contador
    onIncrement: () -> Unit, //Función lambda que se llama cuando incrementa el contador
    modifier: Modifier = Modifier
) {
    // Constantes que no se modifican y contienen las url para los botones
    val context = LocalContext.current //Contexto actual
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/SENAComunica?ref_src=twsrc%5Etfw%7Ctwcamp%5Eembeddedtimeline%7Ctwterm%5Escreen-name%3ASENAComunica%7Ctwcon%5Es2")) }
    val facebook = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SENAMosqueraOficial/")) }
    val whatsapp = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send/?phone=573168760255&text&app_absent=0")) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(width = 250.dp, height = 250.dp),
            painter = painterResource(R.drawable.co_atencion_cliente),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.Titulo_contenido),
            style = MaterialTheme.typography.h2.copy(
            fontWeight = FontWeight.ExtraBold,
            ),
        )
        Text(
            text = stringResource(R.string.Horarios_semana),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(R.string.Horarios_fin_semana),
            style = MaterialTheme.typography.subtitle1
        )
        //El contador inicia desde 0
        if (count > 0){
            //This text is present if the button has been clicked
            //at least once; absent otherwise
            Text(
                "Tu turno es el: A$count",
                style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.ExtraBold,
                ),
            )
        }
        Button(
            //Pasamos el parametro
            onClick =  onIncrement,
            Modifier.padding(top = 8.dp),
            /*enabled = count < 10*/) {
            Text("Pedir Turno")
        }
        Text(text = stringResource(R.string.Boton),
            style = MaterialTheme.typography.subtitle1
        )
        //Fila que contiene las redes sociales
        Text(
            text = "Nuestras redes sociales",
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
        ) {
            IconButton(
                modifier = Modifier.size(45.dp),
                // Acción a realizar cuando se hace clic en el botón
                onClick = { context.startActivity(whatsapp) }
            ) {
                Image(
                    painter = painterResource(R.drawable.co_log_whatsapp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            IconButton(
                modifier = Modifier.size(40.dp),
                // Acción a realizar cuando se hace clic en el botón
                onClick = { context.startActivity(facebook) }
            ) {
                Image(
                    painter = painterResource(R.drawable.log_facebook),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            IconButton(
                modifier = Modifier.size(40.dp),
                // Acción a realizar cuando se hace clic en el botón
                onClick = { context.startActivity(intent) }
            ) {
                Image(
                    painter = painterResource(R.drawable.co_logo_twitter),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    // Variable mutable y recordable para almacenar y actualizar el valor del contador
    var count by rememberSaveable{ mutableStateOf(0) }
    //Llama la función componible y pasa el count y modifier  y una función lambda que incrementa el valor de la variable count cuando se llama
    Page_Contenidos(count, { count ++ }, modifier)
}

@Preview
@Composable
fun Page_Contenidos() {
    Proyecto_DashboardTheme() {
        StatefulCounter()
    }
}