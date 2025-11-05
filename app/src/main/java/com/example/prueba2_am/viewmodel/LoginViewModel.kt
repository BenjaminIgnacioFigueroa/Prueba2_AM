package com.example.prueba2_am.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {


    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val emailError = mutableStateOf<String?>(null)
    val passwordError = mutableStateOf<String?>(null)
    val loginError = mutableStateOf<String?>(null)

    private fun validateInputs(): Boolean {
        emailError.value = null
        passwordError.value = null
        loginError.value = null

        var isValid = true

        if (email.value.isBlank()) {
            emailError.value = "El email no puede estar vacio"
            isValid = false
        } else if (!email.value.contains("@") || !email.value.contains(".")) {
            emailError.value = "El email no es valido"
            isValid = false
        }

        if (password.value.isBlank()) {
            passwordError.value = "La contraseña no puede estar vacia"
            isValid = false
        } else if (password.value.length < 6) {
            passwordError.value = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        }

        return isValid
    }
    fun submitLogin(prefs: SharedPreferences): Boolean {
        if (!validateInputs()) {
            return false
        }

        val savedEmail = prefs.getString("email", null)
        val savedPassword = prefs.getString("password", null)

        if (email.value == savedEmail && password.value == savedPassword) {
            loginError.value = null
            return true
        } else {
            loginError.value = "Email o contraseña incorrectos"
            return false
        }
    }
}