package com.example.app.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app.R
import com.example.app.ui.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(
    navHost: NavHostController,
    viewModel: LoginScreenViewModel = viewModel()
){
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.30f),
                Alignment.TopEnd,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login11), contentDescription = "",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp, vertical = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.reloj),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .weight(1f)
                            .size(100.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        text = "Bienvenido",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value){
                Spacer(modifier = Modifier.height(250.dp))
                Text(text = "Inicia sesión", fontSize = 35.sp,
                    style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(39.dp))
                UserForm(
                    isCreateAccount = false
                ){
                    email, password ->
                    Log.d("Recordatorio de medicamentos", "Logueandome con $email y $password")
                    viewModel.singnInwithEmailAndPasswordd(email,password){
                        navHost.navigate(Screens.BottomNav.route)
                    }
                }
            }
            else{
                Spacer(modifier = Modifier.height(250.dp))
                Text(text = "Crea una cuenta", fontSize = 35.sp,
                    style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(39.dp))
                UserForm(
                    isCreateAccount = true
                ){
                    email, password ->
                    Log.d("Recordatorio de medicamentos", "Creando cuenta con $email y $password")
                    viewModel.createUserWithEmailAndPassword(email, password){
                        navHost.navigate(Screens.Login.route)
                    }

                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                    ){
                val text1 =
                    if (showLoginForm.value) "¿No tiene cuenta?"
                else "¿Ya tienes cuenta?"
                val text2 =
                    if (showLoginForm.value) "Registrate"
                    else "Inicia sesión"
                Text(text = text1)
                Text(text = text2,
                modifier = Modifier
                    .clickable { showLoginForm.value = !showLoginForm.value }
                    .padding(start = 5.dp),
                color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = {email, pwd ->}
) {
    val email = rememberSaveable{
        mutableStateOf("")
    }
    val password = rememberSaveable{
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable{
        mutableStateOf(false)
    }
    val valido = remember(email.value, password.value){
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(
            emailState = email
        )
        Spacer(modifier = Modifier.height(5.dp))
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubmitButton(
            textId = if (isCreateAccount) "Crear cuenta" else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
        Spacer(modifier = Modifier.height(11.dp))

    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValido: Boolean,
    onClic: ()->Unit
) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(29.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
        ) {
        Text(text = textId,
        modifier = Modifier
            .padding(4.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
){
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else
         PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId, fontSize = 15.sp)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon ={
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
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
            Icons.Default.VisibilityOff
    else
        Icons.Default.Visibility
    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
        Icon(
            imageVector = image,
            contentDescription = "")
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId : String = "Email"
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
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId, fontSize = 15.sp)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
        )
}
