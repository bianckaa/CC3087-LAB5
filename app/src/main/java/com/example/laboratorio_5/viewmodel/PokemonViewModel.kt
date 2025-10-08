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

    init {
        fetchPokemonList()
    }

    // Función para obtener la lista de Pokémon
    private fun fetchPokemonList() {
        viewModelScope.launch {
            _pokemonList.value = repository.getPokemonList()
        }
    }

    // Función para obtener los detalles de un Pokémon específico
    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            _selectedPokemon.value = repository.getPokemonDetail(name)
        }
    }
}