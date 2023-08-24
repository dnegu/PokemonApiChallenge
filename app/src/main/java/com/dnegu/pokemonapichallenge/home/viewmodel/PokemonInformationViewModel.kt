package com.dnegu.pokemonapichallenge.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonInformationUIEvent
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonListUIEvent
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonEvolutionaryChainUseCase
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonSkillsUseCase
import com.dnegu.pokemonapichallenge.home.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInformationViewModel @Inject
constructor(
    private val getPokemonEvolutionaryChainUseCase: GetPokemonEvolutionaryChainUseCase,
    private val getPokemonSkillsUseCase: GetPokemonSkillsUseCase
) : ViewModel() {

    private val _pokemonInformationEvent = MutableSharedFlow<PokemonInformationUIEvent>()
    val pokemonInformationEvent get() = _pokemonInformationEvent.asSharedFlow()

    fun getEvolutionaryChain(pokemonId: String) = launch {
        _pokemonInformationEvent.emit(PokemonInformationUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPokemonEvolutionaryChainUseCase(pokemonId)
            }.onSuccess { listNewChecklist ->
                _pokemonInformationEvent.emit(PokemonInformationUIEvent.SuccessEvolutionary(listNewChecklist))
            }.onFailure {
                _pokemonInformationEvent.emit(PokemonInformationUIEvent.Error)
            }
        }
        _pokemonInformationEvent.emit(PokemonInformationUIEvent.HideLoading)
    }

    fun getSkills(name: String) = launch {
        _pokemonInformationEvent.emit(PokemonInformationUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPokemonSkillsUseCase(name)
            }.onSuccess { listNewChecklist ->
                _pokemonInformationEvent.emit(PokemonInformationUIEvent.SuccessSkills(listNewChecklist))
            }.onFailure {
                _pokemonInformationEvent.emit(PokemonInformationUIEvent.Error)
            }
        }
        _pokemonInformationEvent.emit(PokemonInformationUIEvent.HideLoading)
    }
}