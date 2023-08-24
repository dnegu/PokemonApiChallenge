package com.dnegu.pokemonapichallenge.home.ui.event

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList

sealed class PokemonListUIEvent{
    data class Success(val listHistory: List<PokemonList>) : PokemonListUIEvent()
    object Error : PokemonListUIEvent()
    object ShowLoading : PokemonListUIEvent()
    object HideLoading : PokemonListUIEvent()
}
