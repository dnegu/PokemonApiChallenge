package com.dnegu.pokemonapichallenge.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonInformationResponse
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonListUIEvent
import com.dnegu.pokemonapichallenge.home.usecases.GetPokemonListUseCase
import com.dnegu.pokemonapichallenge.home.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SharedViewModel @Inject
constructor(
): ViewModel() {
    private val _uiPokemonNameLiveData = MutableLiveData<String>()
    private val uiPokemonNameLiveData: LiveData<String>
        get() = _uiPokemonNameLiveData

    private val _uiPokemonInformationLiveData = MutableLiveData<PokemonInformationResponse>()
    private val uiPokemonInformationLiveData: LiveData<PokemonInformationResponse>
        get() = _uiPokemonInformationLiveData

    val uiState: String get() = uiPokemonNameLiveData.value!!
    val uiPokemonInformationState: PokemonInformationResponse get() = uiPokemonInformationLiveData.value!!

    fun setPokemonName(name: String) {
        _uiPokemonNameLiveData.value = name
    }

    fun setPokemonInformation(name: PokemonInformationResponse) {
        _uiPokemonInformationLiveData.value = name
    }
}