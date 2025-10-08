package com.example.laboratorio_5.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient { // solo habrá unica instancia (singleton)
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: ApiService by lazy {
        Retrofit.Builder() // Comienza configuración
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}