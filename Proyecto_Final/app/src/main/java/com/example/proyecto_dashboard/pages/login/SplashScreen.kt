package com.example.proyecto_dashboard.pages.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.PageScreen
import com.example.proyecto_dashboard.ui.theme.Proyecto_DashboardTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    //Parametro de Navegación
    navController: NavController
) {
    // Lanza una corrutina cuando se muestra la pantalla de bienvenida
    LaunchedEffect(key1 = true) {
        delay(4000) //Tiempo de 4seg
        navController.popBackStack() // Elimina la pantalla de bienvenida
        navController.navigate(PageScreen.Login.name) //Navega al Inicio de Sesión
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF078C03))
    ) {
        // Agrega aquí el contenido de tu pantalla de bienvenida
        Splash()
    }
}

@Composable
fun Splash() {
    // Crea un objeto Animatable para animar el ángulo de rotación
    val rotationAngle = remember { Animatable(0f) }
    // Corrutina para mostrar el contenido animado
    LaunchedEffect(Unit) {
        rotationAngle.animateTo(
            targetValue = 360f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(130.dp))
        Image(
            modifier = Modifier
                .size(250.dp)
                    //Pasamos el parametro de la animación
                .graphicsLayer {
                    rotationZ = rotationAngle.value
                },
            painter = painterResource(id = R.drawable.splash_logo_sena),
            contentDescription = "Logo Sena"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            stringResource(R.string.bienvenido),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 40.sp
            )
        )
        Text(
            stringResource(R.string.splas_description),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 25.sp
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        IconButton(
            modifier = Modifier
                .size(55.dp)
                .background(
                    Color.White,
                    shape = CircleShape
                ),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Filled.AddBusiness,
                contentDescription = null,
                tint = Color(0xFF078C03)
            )
        }
        Spacer(modifier = Modifier.height(225.dp))
        Text(
            text = "Tienda SENA 2023",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color(0x80212421),
            fontWeight = FontWeight.ExtraBold
        )
    }
}


@Preview
@Composable
fun SplasPreview() {
    Proyecto_DashboardTheme() {
        Splash()
    }
}