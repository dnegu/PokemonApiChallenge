package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.repositories.IPokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: IPokemonRepository) {
    suspend operator fun invoke(workerId: Int) = repository.getPokemonListUseCase(workerId)
}