package com.example.prueba2_am.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba2_am.screens.HomeScreen
import com.example.prueba2_am.screens.RegisterScreen
import com.example.prueba2_am.screens.LoginScreen
import com.example.prueba2_am.screens.ProductsScreen
import com.example.prueba2_am.viewmodel.ProductsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {


        composable(route = AppScreens.Home.route) {
            HomeScreen(
                onNavigateToRegister = {
                    navController.navigate(AppScreens.Register.route)
                },
                onNavigateToLogin = {
                    navController.navigate(AppScreens.Login.route)
                }
            )
        }


        composable(route = AppScreens.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }


        composable(route = AppScreens.Login.route) {
            LoginScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onLoginSuccess = {

                    navController.navigate(AppScreens.Products.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(route = AppScreens.Products.route) {


            val viewModel: ProductsViewModel = viewModel()

            val productsList = viewModel.products.value


            ProductsScreen(
                onLogout = {
                    navController.navigate(AppScreens.Home.route) {
                        popUpTo(AppScreens.Products.route) {
                            inclusive = true
                        }
                    }
                },
                products = productsList
            )
        }
    }
}