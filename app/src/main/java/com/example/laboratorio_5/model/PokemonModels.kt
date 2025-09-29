package com.example.laboratorio_5.model

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonDetailResponse(
    val sprites: Sprites
)

data class Sprites(
    val front_default: String? = null,
    val back_default: String? = null,
    val front_shiny: String? = null,
    val back_shiny: String? = null
)