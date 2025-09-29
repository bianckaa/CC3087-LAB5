package com.example.laboratorio_5

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.laboratorio_5.ui.screens.PokemonDetailScreen
import com.example.laboratorio_5.ui.screens.PokemonListScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            PokemonListScreen(onPokemonClick = { name ->
                navController.navigate("detail/$name")
            })
        }
        composable(
            route = "detail/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            PokemonDetailScreen(pokemonName = name, onBackClick = { navController.popBackStack() })
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}
