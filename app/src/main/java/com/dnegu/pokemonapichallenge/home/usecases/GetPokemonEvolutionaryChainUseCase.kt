package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.repositories.IPokemonRepository
import javax.inject.Inject

class GetPokemonEvolutionaryChainUseCase @Inject constructor(private val repository: IPokemonRepository) {
    suspend operator fun invoke(pokemonId: String) = repository.getPokemonEvolutionaryChainUseCase(pokemonId)
}