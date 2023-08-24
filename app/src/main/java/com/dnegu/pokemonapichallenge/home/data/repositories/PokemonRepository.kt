package com.dnegu.pokemonapichallenge.home.data.repositories

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonEvolutionChainResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonSkillsResponse
import com.dnegu.pokemonapichallenge.home.data.services.PokemonApiService
import javax.inject.Inject

class PokemonRepository@Inject constructor(private val apiService: PokemonApiService) :
    IPokemonRepository {
    override suspend fun getPokemonListUseCase(workerId: Int): List<PokemonList> {
        return apiService.getPokemonListUseCase(workerId).results
    }

    override suspend fun getPokemonInformationUseCase(name: String): PokemonInformationResponse {
        return apiService.getPokemonInformationUseCase(name)
    }

    override suspend fun getPokemonEvolutionaryChainUseCase(pokemonId: String): PokemonEvolutionChainResponse {
        return apiService.getPokemonEvolutionaryChainUseCase(pokemonId)
    }

    override suspend fun getPokemonSkillsUseCase(name: String): PokemonSkillsResponse {
        return apiService.getPokemonSkillsUseCase(name)
    }
}