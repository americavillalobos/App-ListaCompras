package com.example.listaapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            Lista(navController)
        }
        composable("add") {
            AddScreen(navController)
        }
        composable("historial") {
            HistorialScreen(navController)
        }
    }
}


