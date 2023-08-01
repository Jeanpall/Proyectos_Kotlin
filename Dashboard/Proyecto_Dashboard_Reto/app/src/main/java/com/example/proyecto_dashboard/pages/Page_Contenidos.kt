package com.example.proyecto_dashboard.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme

@Composable
fun Page_Contenidos(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            onClick =  onIncrement,
            Modifier.padding(top = 8.dp),
            /*enabled = count < 10*/) {
            Text("Pedir Turno")
        }
        Text(text = stringResource(R.string.Boton),
            style = MaterialTheme.typography.subtitle1
        )
        //Fila que contiene las redes sociales
        Text(text = "Nuestras redes sociales" )
        Row(
            modifier = Modifier
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Filled.AddShoppingCart,
                    contentDescription = null
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
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Filled.AddShoppingCart,
                    contentDescription = null
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

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable{ mutableStateOf(0) }
    Page_Contenidos(count, { count ++ }, modifier)
}

@Preview
@Composable
fun Page_Contenidos() {
    Proyecto_DashboardTheme() {
        StatefulCounter()
    }
}