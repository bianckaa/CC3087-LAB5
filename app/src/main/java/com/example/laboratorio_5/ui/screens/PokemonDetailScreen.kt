package com.example.laboratorio_5.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.laboratorio_5.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(pokemonId: Int, viewModel: PokemonViewModel = viewModel()) {
    val pokemonDetail by viewModel.getPokemonDetail(pokemonId).collectAsState(initial = null)

    pokemonDetail?.let { detail ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf(
                detail.sprites.front_default,
                detail.sprites.back_default,
                detail.sprites.front_shiny,
                detail.sprites.back_shiny
            ).forEach { imageUrl ->
                imageUrl?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                }
            }
        }
    }
}
