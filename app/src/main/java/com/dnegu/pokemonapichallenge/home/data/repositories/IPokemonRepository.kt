package com.dnegu.pokemonapichallenge.home.data.repositories

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList

interface IPokemonRepository {
    suspend fun getPokemonListUseCase(workerId: Int): List<PokemonList>
}