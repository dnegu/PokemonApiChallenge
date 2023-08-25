package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.db.dao.PokemonListDao
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.utils.toModel
import javax.inject.Inject

class GetPokemonFavoriteUseCase @Inject constructor(
    private val pokemonListDao: PokemonListDao
) {
    suspend operator fun invoke(): List<PokemonList> =
            pokemonListDao.getFavorites().map { it.toModel() }
}