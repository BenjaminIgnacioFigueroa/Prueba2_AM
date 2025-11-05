package com.example.prueba2_am.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "HuertoHogar",
        )


        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = { onNavigateToRegister() }
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = { onNavigateToLogin() }
        ) {
            Text("Ingresar")
        }
    }
}
