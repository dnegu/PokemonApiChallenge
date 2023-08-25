package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.db.dao.PokemonListDao
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.random.Random

class UpdatePokemonFavoriteUseCase @Inject constructor(
    private val pokemonListDao: PokemonListDao
) {
    suspend operator fun invoke(name: String): Int {
        val rnd = Random.nextInt().absoluteValue%3
        return try {
            pokemonListDao.changeFavorite(name,rnd)
            rnd
        } catch (e : Exception){
            rnd
        }
    }
}