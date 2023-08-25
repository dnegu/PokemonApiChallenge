package com.dnegu.pokemonapichallenge.home.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.dnegu.pokemonapichallenge.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonListBinding
import com.dnegu.pokemonapichallenge.home.adapter.PokemonListAdapter
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonListUIEvent
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonListViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding,PokemonListViewModel>(),
    PokemonListAdapter.OnButtonClickListener {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PokemonListAdapter by lazy {
        PokemonListAdapter(listAdapter, this@PokemonListFragment)
    }
    private var favoriteList  = listOf<PokemonList>()
    private var listAdapter  = listOf<PokemonList>()
    private var listAdapterOrigin  = listOf<PokemonList>()
    override fun getViewModelClass() = PokemonListViewModel::class.java
    override fun getViewBinding() = FragmentPokemonListBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        binding.rcvInspectionHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvInspectionHistory.adapter = adapter
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listHistory.flowWithLifecycle(lifecycle).collectLatest { event ->
                when(event){
                    PokemonListUIEvent.ShowLoading -> {

                    }
                    PokemonListUIEvent.HideLoading -> {

                    }
                    PokemonListUIEvent.Error -> {

                    }
                    is PokemonListUIEvent.Success -> {
                        val list = event.listHistory
                        listAdapter = list
                        listAdapterOrigin = list
                        showPokemonList(listAdapter)
                    }
                    is PokemonListUIEvent.SuccessPokemonInformation -> {
                        sharedViewModel.setPokemonInformation(event.pokemonInformation)
                        findNavController().navigate(
                            R.id.action_pokemonListFragment_to_pokemonInformationFragment
                        )
                    }
                    is PokemonListUIEvent.SuccessFavorite -> {
                        favoriteList = event.listHistory
                        updatePokemonEvolutionary(favoriteList)
                    }
                }
            }
        }

        val fiveSecondInMillis: Long = 5000
        val countDownInterval: Long = 1000

        object : CountDownTimer(fiveSecondInMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                println("Segundos restantes: $secondsRemaining")
            }

            override fun onFinish() {
                updatePokemonEvolutionary(favoriteList,true)
                println("¡Temporizador completado!")
            }
        }.start()
    }

    private fun showPokemonList(listHistory: List<PokemonList>) = with(binding) {
        adapter.setList(listHistory)
    }

    override fun onButtonClick(data: PokemonList) {
        sharedViewModel.setPokemonName(data.name)
        viewModel.getPokemonInformation(sharedViewModel.uiState)
    }

    private fun updatePokemonEvolutionary(listHistory: List<PokemonList>, noChange: Boolean = false) = with(binding) {
        val favoritos = listHistory.map { it.name }
        val indices = listAdapter.mapIndexedNotNull { index, item ->
            if (item.name in favoritos) index else null
        }
        indices.forEach {
            val favorite = listHistory.first {item -> listAdapter[it].name == item.name }.favorite
            listAdapter[it].favorite = if (!noChange) favorite else 0
            adapter.notifyItemChanged(it)
        }
    }

}