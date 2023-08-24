package com.dnegu.pokemonapichallenge.home.ui

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnegu.pokemonapichallenge.R
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonEvolutionaryLineBinding
import com.dnegu.pokemonapichallenge.home.adapter.PokemonEvolutionaryAdapter
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonEvolutionaryLineViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel

class PokemonEvolutionaryLineFragment :
    BaseFragment<FragmentPokemonEvolutionaryLineBinding,PokemonEvolutionaryLineViewModel>(), PokemonEvolutionaryAdapter.OnButtonClickListener {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PokemonEvolutionaryAdapter by lazy {
        PokemonEvolutionaryAdapter(listOf(), this@PokemonEvolutionaryLineFragment)
    }
    override fun getViewModelClass() = PokemonEvolutionaryLineViewModel::class.java
    override fun getViewBinding() = FragmentPokemonEvolutionaryLineBinding.inflate(layoutInflater)
    override fun onButtonClick(data: String) {

    }

    override fun setUpViews() {
        super.setUpViews()

        binding.rcvInspectionHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvInspectionHistory.adapter = adapter

        sharedViewModel.uiPokemonEvolutionChainState.chain.let {
            val list = mutableListOf<String>()
            list.add(it.species.name)
            if(it.evolves_to.isNotEmpty()){
                it.evolves_to.forEach {evolves ->
                    list.add(evolves.species.name)
                    if(evolves.evolves_to.isNotEmpty()){
                        evolves.evolves_to.forEach{evolves2 ->
                            list.add(evolves2.species.name)
                        }
                    }
                }

            }

            showPokemonEvolutionary(list)
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(
                R.id.goToPokemonListFragment
            )
        }

    }

    private fun showPokemonEvolutionary(listHistory: List<String>) = with(binding) {
        adapter.setList(listHistory)
    }


}