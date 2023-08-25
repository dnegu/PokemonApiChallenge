package com.dnegu.pokemonapichallenge.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonEvolutionaryUIEvent
import com.dnegu.pokemonapichallenge.home.usecases.UpdatePokemonFavoriteUseCase
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonFavoriteUseCase
import com.dnegu.pokemonapichallenge.home.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionaryLineViewModel @Inject
constructor(
    private val getPokemonFavoriteUseCase: GetPokemonFavoriteUseCase,
    private val updatePokemonFavoriteUseCase: UpdatePokemonFavoriteUseCase
) : ViewModel() {

    init {
        getPokemonFavorite()
    }
    
    private val _listEvolutionary = MutableSharedFlow<PokemonEvolutionaryUIEvent>()
    val listEvolutionary get() = _listEvolutionary.asSharedFlow()
    private fun getPokemonFavorite() = launch {
        _listEvolutionary.emit(PokemonEvolutionaryUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPokemonFavoriteUseCase()
            }.onSuccess { listNewChecklist ->
                _listEvolutionary.emit(PokemonEvolutionaryUIEvent.SuccessFavorite(listNewChecklist))
            }.onFailure {
                _listEvolutionary.emit(PokemonEvolutionaryUIEvent.Error)
            }
        }
        _listEvolutionary.emit(PokemonEvolutionaryUIEvent.HideLoading)
    }

    fun updatePokemonFavorite(name: String) = launch {
        _listEvolutionary.emit(PokemonEvolutionaryUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                updatePokemonFavoriteUseCase(name)
            }.onSuccess { listNewChecklist ->
                _listEvolutionary.emit(PokemonEvolutionaryUIEvent.SuccessPokemonUpdate(listNewChecklist))
            }.onFailure {
                _listEvolutionary.emit(PokemonEvolutionaryUIEvent.Error)
            }
        }
        _listEvolutionary.emit(PokemonEvolutionaryUIEvent.HideLoading)
    }
}