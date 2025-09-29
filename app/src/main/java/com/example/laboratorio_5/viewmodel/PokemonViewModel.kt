package com.example.laboratorio_5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio_5.data.remote.Pokemon
import com.example.laboratorio_5.data.remote.PokemonDetail
import com.example.laboratorio_5.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    fun loadPokemonList() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPokemonList()
            _pokemonList.value = response.results
        }
    }

    fun getPokemonDetail(id: Int): StateFlow<PokemonDetail?> {
        val detail = MutableStateFlow<PokemonDetail?>(null)
        viewModelScope.launch {
            detail.value = RetrofitInstance.api.getPokemonDetail(id)
        }
        return detail
    }
}
