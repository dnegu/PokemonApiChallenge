package com.dnegu.pokemonapichallenge.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnegu.pokemonapichallenge.R
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonEvolutionaryLineBinding
import com.dnegu.pokemonapichallenge.home.adapter.PokemonEvolutionaryAdapter
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonEvolutionaryUIEvent
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonEvolutionaryLineViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.notify
@AndroidEntryPoint
class PokemonEvolutionaryLineFragment :
    BaseFragment<FragmentPokemonEvolutionaryLineBinding,PokemonEvolutionaryLineViewModel>(), PokemonEvolutionaryAdapter.OnButtonClickListener {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PokemonEvolutionaryAdapter by lazy {
        PokemonEvolutionaryAdapter(listAdapter, this@PokemonEvolutionaryLineFragment)
    }

    private var favoriteList  = listOf<PokemonList>()
    private var listAdapter  = listOf<PokemonList>()
    private var pokemonList  = PokemonList("","")
    override fun getViewModelClass() = PokemonEvolutionaryLineViewModel::class.java
    override fun getViewBinding() = FragmentPokemonEvolutionaryLineBinding.inflate(layoutInflater)
    override fun onButtonClick(data: PokemonList) {
        pokemonList = data
        viewModel.updatePokemonFavorite(data.name)
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
            listAdapter = list.map {item -> PokemonList(item,"",0) }

            showPokemonEvolutionary(listAdapter)
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(
                R.id.goToPokemonListFragment
            )
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listEvolutionary.flowWithLifecycle(lifecycle).collectLatest { event ->
                when(event){
                    is PokemonEvolutionaryUIEvent.SuccessFavorite -> {
                        favoriteList = event.listHistory
                        updatePokemonEvolutionary(favoriteList)
                    }
                    is PokemonEvolutionaryUIEvent.SuccessPokemonUpdate -> {
                        if(event.response == pokemonList.favorite){
                            Toast.makeText(
                                requireContext(),
                                "No se pudo cambiar",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else{
                            when(event.response){
                                0 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "SERVER ERROR",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                1 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Se agregó a favorito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                2 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Ocurrio un error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            pokemonList.favorite = event.response
                            val indice= listAdapter.indexOfFirst { it.name == pokemonList.name}
                            listAdapter[indice].favorite = event.response
                            adapter.notifyItemChanged(indice)
                        }
                    }
                    PokemonEvolutionaryUIEvent.HideLoading -> {

                    }
                    PokemonEvolutionaryUIEvent.ShowLoading -> {

                    }
                    PokemonEvolutionaryUIEvent.Error -> {

                    }
                }
            }
        }
    }

    private fun showPokemonEvolutionary(listHistory: List<PokemonList>) = with(binding) {
        adapter.setList(listHistory)
    }
    private fun updatePokemonEvolutionary(listHistory: List<PokemonList>) = with(binding) {
        val favoritos = listHistory.filter { it.favorite == 1 } .map { it.name }
        val indices = listAdapter.mapIndexedNotNull { index, item ->
            if (item.name in favoritos) index else null
        }
        indices.forEach {
            listAdapter[it].favorite = 1
            adapter.notifyItemChanged(it)
        }
    }

}