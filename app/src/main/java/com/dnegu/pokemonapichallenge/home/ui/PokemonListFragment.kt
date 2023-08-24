package com.dnegu.pokemonapichallenge.home.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.dnegu.pokemonapichallenge.R
import androidx.navigation.fragment.findNavController
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonListBinding
import com.dnegu.pokemonapichallenge.home.ui.event.PokemonListUIEvent
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding,PokemonListViewModel>() {

    override fun getViewModelClass() = PokemonListViewModel::class.java
    override fun getViewBinding() = FragmentPokemonListBinding.inflate(layoutInflater)
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
                        list.size
                    }
                }
            }
        }
    }

    override fun observeView() {
        super.observeView()
        binding.btnInformation.setOnClickListener {
            findNavController().navigate(
                R.id.action_pokemonListFragment_to_pokemonInformationFragment
            )
        }
    }

}