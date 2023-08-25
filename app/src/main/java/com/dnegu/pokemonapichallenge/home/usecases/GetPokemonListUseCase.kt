package com.dnegu.pokemonapichallenge.home.usecases

import com.dnegu.pokemonapichallenge.home.data.db.dao.PokemonListDao
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.data.repositories.IPokemonRepository
import com.dnegu.pokemonapichallenge.home.utils.toEntity
import com.dnegu.pokemonapichallenge.home.utils.toModel
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: IPokemonRepository,
    private val pokemonListDao: PokemonListDao
) {
    suspend operator fun invoke(workerId: Int): List<PokemonList> {
        val listPokemon = pokemonListDao.getAll()
        return if(listPokemon.isEmpty()){
            val listRepository = repository.getPokemonListUseCase(workerId)
            val listEntity = listRepository.map { it.toEntity() }
            pokemonListDao.insertMultiple(*listEntity.toTypedArray())
            listRepository
        } else
            listPokemon.map { it.toModel() }
    }
}