package com.example.laboratorio_5.data.repository

import com.example.laboratorio_5.data.remote.ApiService
import com.example.laboratorio_5.data.remote.PokemonDetailResponse
import com.example.laboratorio_5.data.remote.PokemonResult

class PokemonRepository(private val api: ApiService) {
    // Obtener lista de Pokémon desde la API
    suspend fun getPokemonList(limit: Int = 100, offset: Int = 0): List<PokemonResult> {
        return try {
            val response = api.getPokemonList(limit, offset)
            response.results
        } catch (e: Exception) {
            e.printStackTrace() // error registrado
            emptyList() // devuelve un valor seguro
        }
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse? {
        return try {
            api.getPokemonDetail(name)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}