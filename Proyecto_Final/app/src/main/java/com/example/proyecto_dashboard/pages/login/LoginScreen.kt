package com.example.proyecto_dashboard.pages.login

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.MenuItem
import com.example.proyecto_dashboard.components.notificacionImagen
import com.example.proyecto_dashboard.components.notificacionSencilla

@Composable
fun LoginScreen(navController: NavHostController) {
    //Variable para controlar la accion: True::Login - false::Create
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    // Detecta cualquier Gif, en el contexto actual
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Color de fondo
        BoxWithConstraints (
            modifier = Modifier
                .background(Color(0xFF8BC34A))
                ) {
            //Valores para la posicion de la Box
            val boxWidth = maxWidth
            val boxHeight = maxHeight

            Image(
                // Utiliza el método rememberAsyncImagePainter para cargar la imagen de forma asíncrona
                painter = rememberAsyncImagePainter(
                    // Construye un objeto ImageRequest utilizando el método Builder y pasándole el contexto actual
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.splash_login)
                        // Aplica un bloque de código al objeto Builder
                        .apply(block = fun ImageRequest.Builder.() {
                            size(Size.ORIGINAL)
                        }).build(),
                    imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier  = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .offset(
                        //Horizontal
                        x = (boxWidth - 400.dp) / 2,
                        //Vertical
                        y = (boxHeight - 1.dp) / 2
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    // Verifica si se puede mostrar el formulario
                    if (showLoginForm.value) {
                        Text(
                            text = "Inicia Sesión",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // Mostrar el formulario de Inicio o Registro
                        UserForm(
                            isCreateAccount = false,
                            // Función lambda para manejar el evento de enviar el formulario
                            { email, password ->
                                // Registra un mensaje en el log con la información del usuario
                                Log.d("TiendaApp", "Inicio sesion con $email y $password")
                            },
                            navController = navController
                        )
                        // Si no muestra el formulario de Inicio de Sesión, entonces que muestre el de Registro
                    } else {
                        Text(
                            text = "Crear Cuenta Nueva",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        UserForm(
                            isCreateAccount = true,
                            { email, password ->
                                Log.d("TiendaApp", "Creando Cuenta con $email y $password")
                            },
                            navController = navController
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val text1 =
                            if (showLoginForm.value)
                                "¿No Tienes Cuenta?"
                            else
                                "¿Ya Tienes Cuenta?"
                        val text2 =
                            if (showLoginForm.value)
                                "Registrate"
                            else
                                "Inicia Sesión"
                        Text(
                            text = text1,
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = text2,
                            modifier = Modifier
                                .clickable { showLoginForm.value = !showLoginForm.value }
                                .padding(start = 5.dp),
                            color = Color.Blue,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.log_google),
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.log_facebook),
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    //Parametros para el formulario
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = { email, pwd -> },
    navController: NavHostController
) {
    //Variable que recuerda el Email
    val email = rememberSaveable {
        mutableStateOf("")
    }
    //Variable que recuerda el Password
    val password = rememberSaveable {
        mutableStateOf("")
    }
    //Variable que recuerda el mostrar o ocultar la contraseña
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    // Variable para almacenar si los campos de correo electrónico y contraseña son válidos
    val validState = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    // Obtiene el controlador del teclado virtual
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Componente Input para el campo Correo
        EmailInput(
            emailState = email,
        )
        //Componente Input para el campo Password
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        //Boton para enviar el formulario
        SubmitButton(
            textId =
            if (isCreateAccount)
                "Crear Cuenta"
            else
                "Iniciar Sesión",
            inputValid = validState,
            isCreateAccount = isCreateAccount,
            onClic = {
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
             },
            navController = navController
        )
    }
}

@Composable
fun SubmitButton(
    //Parametros
    textId: String,
    inputValid: Boolean,
    isCreateAccount: Boolean,
    onClic: () -> Unit,
    navController: NavHostController
) {
    //Valores de notificaciones
    val idNotification = 0 // Identificador de la notificación
    val context: Context = LocalContext.current // Obtiene el contexto actual
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort = "Su Inicio de Sesion se a realizado con exito."
    val textLong: String = "Saludos! Usted se a Registrado Exitosamente " +
            "El CBA le agradece que nos acompañe en cada momento "

    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8BC34A),
            contentColor = Color.White,
        ),
        onClick = {
            onClic() // Llama a la función lambda onClic()
            if (isCreateAccount) {
                // Enviar notificación de bienvenida al registrarse
                notificacionImagen(
                    context,
                    idChannel,
                    idNotification + 2,
                    "Notificacion De Registro",
                    textLong,
                    imagenBig
                )
            } else {
                // Enviar notificación de inicio de sesión exitoso
                notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Notificacion Login",
                    textShort
                )
            }
            //Navegación a la ruta especificada
            navController.navigate(MenuItem.Page01.ruta)
        },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValid // Establece si el botón está habilitado o no
    ) {
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    //Parametros
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    //Variable que almacena y permite verificar si debe o no mostrarla
    val visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    //  Campo de entrada con Borde
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.Green
        ),
        // Establece el valor del campo de contraseña utilizando el valor de la variable passwordState
        value = passwordState.value,
        // Función lambda para manejar el evento de cambio de valor
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId, color = Color.White, fontWeight = FontWeight.ExtraBold) },
        // Establece que el campo de contraseña solo debe tener una línea
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        // Establece la transformación visual del campo de contraseña utilizando la variable
        visualTransformation = visualTransformation,
        //Icono a mostrar
        trailingIcon = {
            //Verifica que no este en blanco y pasa la variable passwordVisible como estado
            if (passwordState.value.isNotBlank())
                PasswordVisibleIcon(passwordVisible)
            else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    //Parametro para ocultar o mostrar la password
    passwordVisible: MutableState<Boolean>
) {
    //Variable para almacenar los Iconos
    val image =
        // Verifica si se debe mostrar u ocultar la contraseña
        if (passwordVisible.value)
            Icons.Default.Visibility
        else
            Icons.Default.VisibilityOff
    IconButton(
        onClick = {
            // Invierte el valor de la variable passwordVisible para mostrar/ocultar la contraseña
            passwordVisible.value = !passwordVisible.value
        }) {
        Icon(
            imageVector = image,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun EmailInput(
    //Parametros
    emailState: MutableState<String>,
    labelId: String = "Email",
) {
    //Campo de entrada de Texto
    InputField(
        valueState = emailState,
        labelId = labelId,
        //Teclado tipo correo electrónico
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    //Parametros
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true,
) {
    OutlinedTextField(
        value = valueState.value,
        // Función lambda para manejar el evento de cambio de valor del campo de entrada de texto
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId, color = Color.White, fontWeight = FontWeight.ExtraBold) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        //Opciones para el teclado virtual y se pasa el parametro keyboardType
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.Green
        )
    )
}