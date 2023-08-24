package com.dnegu.pokemonapichallenge.home.data.services

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonEvolutionChainResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonListResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonSkillsResponse
import retrofit2.http.*
interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonListUseCase(@Query ("limit") limit: Int): PokemonListResponse

    @GET("pokemon-species/{name}/")
    suspend fun getPokemonInformationUseCase(@Path ("name") name: String): PokemonInformationResponse

    @GET("evolution-chain/{id}/")
    suspend fun getPokemonEvolutionaryChainUseCase(@Path ("id") id: String): PokemonEvolutionChainResponse
    @GET("pokemon/{name}/")
    suspend fun getPokemonSkillsUseCase(@Path ("name") name: String): PokemonSkillsResponse
}