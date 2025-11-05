package com.example.prueba2_am.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    val name: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val confirmPassword: MutableState<String> = mutableStateOf("")
    val profileImageBitmap: MutableState<Bitmap?> = mutableStateOf(null)

    val nameError: MutableState<String?> = mutableStateOf(null)
    val emailError: MutableState<String?> = mutableStateOf(null)
    val passwordError: MutableState<String?> = mutableStateOf(null)
    val confirmPasswordError: MutableState<String?> = mutableStateOf(null)


    fun submitForm(): Boolean {
        return validateInputs()
    }


    private fun isEmailValid(email: String): Boolean {
        return Pattern.matches(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",
            email
        )
    }

    private fun validateInputs(): Boolean {
        nameError.value = null
        emailError.value = null
        passwordError.value = null
        confirmPasswordError.value = null

        var isValid = true


        if (name.value.isBlank()) {
            nameError.value = "El nombre no puede estar vacío"
            isValid = false
        }


        if (email.value.isBlank()) {
            emailError.value = "El email no puede estar vacío"
            isValid = false
        } else if (!isEmailValid(email.value)) {
            emailError.value = "El email no es válido"
            isValid = false
        }


        if (password.value.isBlank()) {
            passwordError.value = "La contraseña no puede estar vacía"
            isValid = false
        } else if (password.value.length < 6) {
            passwordError.value = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        }


        if (confirmPassword.value.isBlank()) {
            confirmPasswordError.value = "Confirma la contraseña"
            isValid = false
        } else if (password.value != confirmPassword.value) {
            confirmPasswordError.value = "Las contraseñas no coinciden"
            isValid = false
        }

        return isValid
    }
}