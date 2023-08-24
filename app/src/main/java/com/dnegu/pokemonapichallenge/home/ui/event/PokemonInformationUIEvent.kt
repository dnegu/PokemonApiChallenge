package com.dnegu.pokemonapichallenge.home.ui.event

import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonEvolutionChainResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonSkillsResponse

sealed class PokemonInformationUIEvent{
    data class SuccessEvolutionary(val pokemonEvolutionChainResponse: PokemonEvolutionChainResponse) : PokemonInformationUIEvent()
    data class SuccessSkills(val pokemonSkillsResponse: PokemonSkillsResponse) : PokemonInformationUIEvent()
    object Error : PokemonInformationUIEvent()
    object ShowLoading : PokemonInformationUIEvent()
    object HideLoading : PokemonInformationUIEvent()
}
