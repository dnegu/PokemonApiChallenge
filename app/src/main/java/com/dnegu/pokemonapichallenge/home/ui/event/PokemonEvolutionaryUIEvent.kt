package com.dnegu.pokemonapichallenge.home.ui.event

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList

sealed class PokemonEvolutionaryUIEvent{
    data class SuccessFavorite(val listHistory: List<PokemonList>) : PokemonEvolutionaryUIEvent()
    data class SuccessPokemonUpdate(val response: Int) : PokemonEvolutionaryUIEvent()
    object Error : PokemonEvolutionaryUIEvent()
    object ShowLoading : PokemonEvolutionaryUIEvent()
    object HideLoading : PokemonEvolutionaryUIEvent()
}
