package com.example.prueba2_am.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.prueba2_am.components.ProfileImagePicker
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.*
import com.example.prueba2_am.viewmodel.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    val name = remember { viewModel.name }
    val email = remember { viewModel.email }
    val password = remember { viewModel.password }
    val confirmPassword = remember { viewModel.confirmPassword }
    val profileImageBitmap = remember { viewModel.profileImageBitmap }
    val nameError = remember { viewModel.nameError }
    val emailError = remember { viewModel.emailError }
    val passwordError = remember { viewModel.passwordError }
    val confirmPasswordError = remember { viewModel.confirmPasswordError }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta") },
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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(24.dp))
            ProfileImagePicker(
                currentBitmap = profileImageBitmap.value,
                onBitmapReady = { newBitmap ->
                    profileImageBitmap.value = newBitmap
                }
            )

            Text(
                "Tocar para tomar foto (OPCIONAL)",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre") },
                isError = nameError.value != null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = nameError.value != null,
                enter = slideInVertically { -it/2 } + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically { -it/2 } + fadeOut(targetAlpha = 0f)
            ) {
                Text(
                    text = nameError.value ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text("Confirmar Contraseña") },
                isError = confirmPasswordError.value != null,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = confirmPasswordError.value != null,
                enter = slideInVertically { -it/2 } + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically { -it/2 } + fadeOut(targetAlpha = 0f)
            ) {
                Text(
                    text = confirmPasswordError.value ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (viewModel.submitForm()) {
                        val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

                        prefs.edit().apply {
                            putString("email", email.value)
                            putString("password", password.value)
                            apply()
                        }

                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Usuario registrado correctamente")
                            onNavigateBack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrarse")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
