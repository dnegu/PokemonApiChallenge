package com.dnegu.pokemonapichallenge.home.data.repositories

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList

interface IPokemonRepository {
    suspend fun getPokemonListUseCase(workerId: Int): List<PokemonList>
    suspend fun getPokemonInformationUseCase(workerId: String): PokemonInformationResponse
}