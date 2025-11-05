package com.example.prueba2_am.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.compose.animation.*
import com.example.prueba2_am.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    val email = remember { viewModel.email }
    val password = remember { viewModel.password }
    val emailError = remember { viewModel.emailError }
    val passwordError = remember { viewModel.passwordError }
    val loginError = remember { viewModel.loginError }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val prefs = remember {
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Iniciar Sesión") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))


            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                isError = emailError.value != null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = emailError.value != null,
                enter = slideInVertically { -it/2 } + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically { -it/2 } + fadeOut(targetAlpha = 0f)
            ) {
                Text(
                    text = emailError.value ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                isError = passwordError.value != null,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            AnimatedVisibility(
                visible = passwordError.value != null,
                enter = slideInVertically { -it/2 } + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically { -it/2 } + fadeOut(targetAlpha = 0f)
            ) {
                Text(
                    text = passwordError.value ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }


            AnimatedVisibility(
                visible = loginError.value != null,
                enter = slideInVertically { -it/2 } + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically { -it/2 } + fadeOut(targetAlpha = 0f)
            ) {
                Text(
                    text = loginError.value ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }



            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {

                    if (viewModel.submitLogin(prefs)) {


                        val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)


                        vibrator?.let {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                it.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                            } else {
                                @Suppress("DEPRECATION")
                                it.vibrate(200)
                            }
                        }

                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Login exitoso")
                        }
                        onLoginSuccess()

                    } else {

                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Entrar")
            }
        }
    }
}

