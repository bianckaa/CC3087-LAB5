package com.example.laboratorio_5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio_5.data.remote.RetrofitInstance
import com.example.laboratorio_5.model.PokemonDetailResponse
import com.example.laboratorio_5.model.PokemonResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    private val _selectedPokemon = MutableStateFlow<PokemonDetailResponse?>(null)
    val selectedPokemon: StateFlow<PokemonDetailResponse?> = _selectedPokemon

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList(limit = 100, offset = 0)
                _pokemonList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonDetail(name)
                _selectedPokemon.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}