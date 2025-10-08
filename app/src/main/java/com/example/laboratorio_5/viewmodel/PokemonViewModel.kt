package com.example.laboratorio_5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio_5.data.remote.RetrofitClient
import com.example.laboratorio_5.data.remote.PokemonDetailResponse
import com.example.laboratorio_5.data.remote.PokemonResult
import com.example.laboratorio_5.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository = PokemonRepository(RetrofitClient.api)
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    private val _selectedPokemon = MutableStateFlow<PokemonDetailResponse?>(null)
    val selectedPokemon: StateFlow<PokemonDetailResponse?> = _selectedPokemon

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchPokemonList()
    }

    // Función para obtener la lista de Pokémon
    private fun fetchPokemonList() {
        viewModelScope.launch {
            val result = repository.getPokemonList()
            if (result.isEmpty()) {
                _errorMessage.value = "No se puedo cargar la lista de Pokémon correctamente"
            } else {
                _pokemonList.value = result
            }
        }
    }

    // Función para obtener los detalles de un Pokémon específico
    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            val result =  repository.getPokemonDetail(name)
            if (result == null) {
                _errorMessage.value = "No se puedo cargar los detalles del Pokémon"
            } else {
                _selectedPokemon.value = result
            }
        }
    }
}