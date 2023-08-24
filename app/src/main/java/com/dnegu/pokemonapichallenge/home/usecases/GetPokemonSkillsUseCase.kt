package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.repositories.IPokemonRepository
import javax.inject.Inject

class GetPokemonSkillsUseCase @Inject constructor(private val repository: IPokemonRepository) {
    suspend operator fun invoke(name: String) = repository.getPokemonSkillsUseCase(name)
}