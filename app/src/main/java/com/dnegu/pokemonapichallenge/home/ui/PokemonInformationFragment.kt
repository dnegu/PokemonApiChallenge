package com.dnegu.pokemonapichallenge.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dnegu.pokemonapichallenge.R
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonInformationBinding
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonInformationUIEvent
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonInformationViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        btnEvolutionaryLine.setOnClickListener {
            val pokemonId = sharedViewModel.uiPokemonInformationState.evolution_chain.url.substringAfterLast("/")
            viewModel.getEvolutionaryChain(pokemonId)
        }

        btnSkills.setOnClickListener {
            viewModel.getSkills(sharedViewModel.uiState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonInformationEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    PokemonInformationUIEvent.ShowLoading -> {}
                    PokemonInformationUIEvent.HideLoading -> {}
                    PokemonInformationUIEvent.Error -> {}
                    is PokemonInformationUIEvent.SuccessEvolutionary -> {
                        sharedViewModel.setPokemonEvolutionChain(event.pokemonEvolutionChainResponse)
                        findNavController().navigate(
                            R.id.action_pokemonInformationFragment_to_pokemonEvolutionaryLineFragment
                        )
                    }
                    is PokemonInformationUIEvent.SuccessSkills -> {
                        sharedViewModel.setPokemonSkills(event.pokemonSkillsResponse)
                        findNavController().navigate(
                            R.id.action_pokemonInformationFragment_to_pokemonSkillsFragment
                        )
                    }
                }
            }
        }
    }
}