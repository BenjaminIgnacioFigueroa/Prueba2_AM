package com.example.prueba2_am.model

data class Product(
    val id: String,
    val nombre: String,
    val precio: Int,
    val unidad: String,
    val stock: Int,
    val categoria: String,
    val descripcion: String
)