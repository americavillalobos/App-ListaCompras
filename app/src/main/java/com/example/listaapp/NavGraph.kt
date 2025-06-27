package com.example.listaapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "lista") {
        composable("lista") {
            Lista(navController)
        }
        composable("add") {
            AddScreen(navController = navController, productoId = 0)
        }
        composable(
            "edit/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0
            AddScreen(navController = navController, productoId = productoId)
        }
        composable("historial") {
            HistorialScreen(navController)
        }
    }

}




