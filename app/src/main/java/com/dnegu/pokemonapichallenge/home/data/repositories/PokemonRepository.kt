package com.dnegu.pokemonapichallenge.home.data.repositories

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.data.services.PokemonApiService
import javax.inject.Inject

class PokemonRepository@Inject constructor(private val apiService: PokemonApiService) :
    IPokemonRepository {
    override suspend fun getPokemonListUseCase(workerId: Int): List<PokemonList> {
        return apiService.getPokemonListUseCase(workerId).results
    }
}