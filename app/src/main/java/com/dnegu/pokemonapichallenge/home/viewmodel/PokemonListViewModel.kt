package com.dnegu.pokemonapichallenge.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonListUIEvent
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonInformationUseCase
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonListUseCase
import com.dnegu.pokemonapichallenge.home.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject
constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonInformationUseCase: GetPokemonInformationUseCase,
): ViewModel() {
    init {
        getPokemonList(151)
    }

    private val _listHistory = MutableSharedFlow<PokemonListUIEvent>()
    val listHistory get() = _listHistory.asSharedFlow()

    private fun getPokemonList(limit: Int) = launch {
        _listHistory.emit(PokemonListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPokemonListUseCase(limit)
            }.onSuccess { listNewChecklist ->
                _listHistory.emit(PokemonListUIEvent.Success(listNewChecklist))
            }.onFailure {
                _listHistory.emit(PokemonListUIEvent.Error)
            }
        }
        _listHistory.emit(PokemonListUIEvent.HideLoading)
    }

    fun getPokemonInformation(name: String) = launch {
        _listHistory.emit(PokemonListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPokemonInformationUseCase(name)
            }.onSuccess { listNewChecklist ->
                _listHistory.emit(PokemonListUIEvent.SuccessPokemonInformation(listNewChecklist))
            }.onFailure {
                _listHistory.emit(PokemonListUIEvent.Error)
            }
        }
        _listHistory.emit(PokemonListUIEvent.HideLoading)
    }
}