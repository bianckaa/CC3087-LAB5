package com.example.laboratorio_5.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon") // Endpoint para obtener la lista de Pokémon
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100, // Cuantos elementos traer
        @Query("offset") offset: Int = 0 // Desde que elementos comenzar a traer
    ): PokemonListResponse

    @GET("pokemon/{name}") // Endpoint para obtener los detalles de un Pokémon específico
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}