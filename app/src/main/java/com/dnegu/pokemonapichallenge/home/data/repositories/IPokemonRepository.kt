package com.dnegu.pokemonapichallenge.home.data.repositories

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonEvolutionChainResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonSkillsResponse

interface IPokemonRepository {
    suspend fun getPokemonListUseCase(workerId: Int): List<PokemonList>
    suspend fun getPokemonInformationUseCase(pokemonName: String): PokemonInformationResponse
    suspend fun getPokemonEvolutionaryChainUseCase(pokemonId: String): PokemonEvolutionChainResponse
    suspend fun getPokemonSkillsUseCase(pokemonId: String): PokemonSkillsResponse
}