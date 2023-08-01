package com.example.compose_login.pages.login

import android.content.Context
import android.graphics.BitmapFactory
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_login.R
import com.example.compose_login.components.CreateChannelNotification
import com.example.compose_login.components.notificacionImagen
import com.example.compose_login.components.notificacionSencilla
import com.example.compose_login.ui.theme.Compose_loginTheme

@Composable
fun LoginScreen() {
    //Variable para controlar la accion: True::Login - false::Create
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BoxWithConstraints (
            modifier = Modifier
                .background(Color(0xFF8BC34A))
                ) {
            //Valores para la posicion de la Box
            val boxWidth = maxWidth
            val boxHeight = maxHeight

            Box(
                modifier = Modifier
                    .offset(
                        //Horizontal
                        x = (boxWidth - 397.dp) / 2,
                        //Vertical
                        y = (boxHeight - 380.dp) / 2
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier.background(Color(0xFFFFFFFF)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.splash_sena_logo),
                        contentDescription = "Logo Sena"
                    )
                    if (showLoginForm.value) {
                        Text(
                            text = "Inicia Sesión"
                        )
                        UserForm(
                            isCreateAccount = false
                        ) { email, password ->
                            Log.d("TiendaApp", "Inicio sesion con $email y $password")
                        }
                    } else {
                        Text(
                            text = "Crear Cuenta Nueva"
                        )
                        UserForm(
                            isCreateAccount = true
                        ) { email, password ->
                            Log.d("TiendaApp", "Creando Cuenta con $email y $password")
                        }
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
                            text = text1
                        )
                        Text(
                            text = text2,
                            modifier = Modifier
                                .clickable { showLoginForm.value = !showLoginForm.value }
                                .padding(start = 5.dp),
                            color = Color.Blue
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
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = { email, pwd -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(true)
    }
    val validState = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        SubmitButton(
            textId =
            if (isCreateAccount)
                "Crear Cuenta"
            else
                "Iniciar Sesión",
            inputValid = validState,
            isCreateAccount = isCreateAccount
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValid: Boolean,
    isCreateAccount: Boolean,
    onClic: () -> Unit
) {
    //Valores de notificaciones
    val idNotification = 0
    val context: Context = LocalContext.current
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
        onClick = {
            onClic()
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
        },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValid
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
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId) },
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
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank())
                PasswordVisibleIcon(passwordVisible)
            else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.Visibility
        else
            Icons.Default.VisibilityOff
    IconButton(
        onClick = {
            passwordVisible.value = !passwordVisible.value
        }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    Compose_loginTheme() {
        LoginScreen()
    }
}