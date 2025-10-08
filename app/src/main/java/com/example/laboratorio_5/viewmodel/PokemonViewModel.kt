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
            try {
                val result = repository.getPokemonList()
                if (result.isEmpty()) {
                    _errorMessage.value = "No se pudo cargar la lista de Pokémon correctamente 😢"
                } else {
                    _pokemonList.value = result
                    _errorMessage.value = null
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar la lista de Pokémon"
            }
        }
    }

    // Función para obtener los detalles de un Pokémon específico
    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val detail = repository.getPokemonDetail(name)
                _selectedPokemon.value = detail
                _errorMessage.value = null
            } catch (e: Exception) {
                _selectedPokemon.value = null
                _errorMessage.value = "Error al cargar los detalles de $name💔"
            }
        }
    }
}