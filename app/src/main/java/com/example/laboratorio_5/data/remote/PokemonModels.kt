package com.example.laboratorio_5.data.remote

// Respuesta al pedir la lista de pokémon
data class PokemonListResponse(
    val results: List<PokemonResult>
)

// Elementos de esa lista con su correspondiente nombre y URL
data class PokemonResult(
    val name: String,
    val url: String
)

// Detalles del pokémon en específico
data class PokemonDetailResponse(
    val sprites: Sprites
)

// URL´s de las imagenes del pokémon (front, back, shiny)
data class Sprites(
    val front_default: String? = null,
    val back_default: String? = null,
    val front_shiny: String? = null,
    val back_shiny: String? = null
)