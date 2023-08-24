package com.dnegu.pokemonapichallenge.home.data.services

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonListResponse
import retrofit2.http.*
interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonListUseCase(@Query ("limit") limit: Int): PokemonListResponse
}