package com.example.laboratorio_5.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laboratorio_5.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonViewModel = viewModel()
) {

    // Se llama solamente una vez a loadPokemonList()
    LaunchedEffect(Unit) {
        viewModel.loadPokemonList()
    }

    val pokemonList by viewModel.pokemonList.collectAsState()

    LazyColumn {
        items(pokemonList.size) { index ->
            val pokemon = pokemonList[index]
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Extraer ID del URL
                        val id = pokemon.url.split("/").dropLast(1).last()
                        navController.navigate("detail/$id")
                    }
                    .padding(16.dp)
            )
        }
    }
}
