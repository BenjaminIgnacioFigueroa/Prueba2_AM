package com.example.prueba2_am.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prueba2_am.model.Product

class ProductsViewModel : ViewModel() {

    val products = mutableStateOf<List<Product>>(emptyList())

    init {
        loadProducts()
    }

    private fun loadProducts() {
        products.value = listOf(
            Product(
                id = "FR001",
                nombre = "Manzanas Fuji",
                precio = 1200,
                unidad = "kilo",
                stock = 150,
                categoria = "Frutas Frescas",
                descripcion = "Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule. Perfectas para meriendas saludables o como ingrediente en postres."
            ),
            Product(
                id = "FR002",
                nombre = "Naranjas Valencia",
                precio = 1000,
                unidad = "kilo",
                stock = 200,
                categoria = "Frutas Frescas",
                descripcion = "Jugosas y ricas en vitamina C, ideales para zumos frescos y refrescantes."
            ),
            Product(
                id = "FR003",
                nombre = "Plátanos Cavendish",
                precio = 800,
                unidad = "kilo",
                stock = 180,
                categoria = "Frutas Frescas",
                descripcion = "Plátanos maduros y dulces, perfectos para el desayuno o como snack energético."
            ),
            Product(
                id = "VR001",
                nombre = "Zanahorias Orgánicas",
                precio = 900,
                unidad = "kilo",
                stock = 120,
                categoria = "Verduras Orgánicas",
                descripcion = "Zanahorias crujientes cultivadas sin pesticidas. Excelente fuente de vitamina A y fibra."
            ),
            Product(
                id = "VR002",
                nombre = "Espinacas Frescas",
                precio = 700,
                unidad = "bolsa de 500g",
                stock = 90,
                categoria = "Verduras Orgánicas",
                descripcion = "Espinacas frescas y nutritivas, ideales para ensaladas y batidos verdes."
            ),
            Product(
                id = "VR003",
                nombre = "Pimientos Tricolores",
                precio = 1500,
                unidad = "kilo",
                stock = 75,
                categoria = "Verduras Orgánicas",
                descripcion = "Pimientos rojos, amarillos y verdes, ideales para salteados y platos coloridos."
            ),
            Product(
                id = "PO001",
                nombre = "Miel Orgánica",
                precio = 5000,
                unidad = "frasco de 500g",
                stock = 60,
                categoria = "Productos Orgánicos",
                descripcion = "Miel pura y orgánica producida por apicultores locales. Rica en antioxidantes y con un sabor inigualable."
            ),
            Product(
                id = "PO003",
                nombre = "Quinua Orgánica",
                precio = 6000,
                unidad = "sobre de 400g",
                stock = 85,
                categoria = "Productos Orgánicos",
                descripcion = "Grano nutritivo y libre de pesticidas, ideal para ensaladas, guisos y platos saludables."
            ),
            Product(
                id = "PL001",
                nombre = "Leche Entera",
                precio = 1000,
                unidad = "litro",
                stock = 110,
                categoria = "Productos Lácteos",
                descripcion = "Leche fresca de granjas locales, rica en calcio y nutrientes esenciales, perfecta para toda la familia."
            )
        )
    }
}