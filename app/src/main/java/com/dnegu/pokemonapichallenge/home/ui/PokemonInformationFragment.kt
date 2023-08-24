package com.dnegu.pokemonapichallenge.home.ui

import androidx.fragment.app.activityViewModels
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonInformationBinding
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonInformationViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel

class PokemonInformationFragment : BaseFragment<FragmentPokemonInformationBinding,PokemonInformationViewModel>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun getViewModelClass() = PokemonInformationViewModel::class.java
    override fun getViewBinding() = FragmentPokemonInformationBinding.inflate(layoutInflater)

    override fun setUpViews() = with(binding) {
        super.setUpViews()

        tvNamePokemon.text = sharedViewModel.uiState

        sharedViewModel.uiPokemonInformationState.let{ information ->
            tvBaseHappiness.text = information.base_happiness.toString()
            tvColorPokemon.text = information.color.name
            tvCaptureRate.text = information.capture_rate.toString()
            tvGroupEgg.text = information.egg_groups.joinToString(", ") { it.name }
        }
    }
}