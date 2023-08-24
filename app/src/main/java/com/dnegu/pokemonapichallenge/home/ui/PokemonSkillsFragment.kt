package com.dnegu.pokemonapichallenge.home.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnegu.pokemonapichallenge.R
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonListBinding
import com.dnegu.pokemonapichallenge.databinding.FragmentPokemonSkillsBinding
import com.dnegu.pokemonapichallenge.home.adapter.PokemonListAdapter
import com.dnegu.pokemonapichallenge.home.adapter.PokemonSkillAdapter
import com.dnegu.pokemonapichallenge.home.data.model.response.Ability
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import com.dnegu.pokemonapichallenge.home.utils.BaseFragment
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonListViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonSkillsViewModel
import com.dnegu.pokemonapichallenge.home.viewmodel.SharedViewModel

class PokemonSkillsFragment : BaseFragment<FragmentPokemonSkillsBinding, PokemonSkillsViewModel>(),
        PokemonSkillAdapter.OnButtonClickListener
{

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PokemonSkillAdapter by lazy {
        PokemonSkillAdapter(listOf(), this@PokemonSkillsFragment)
    }
    override fun getViewModelClass() = PokemonSkillsViewModel::class.java
    override fun getViewBinding() = FragmentPokemonSkillsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        binding.rcvInspectionHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvInspectionHistory.adapter = adapter

        sharedViewModel.uiPokemonSkillsState.abilities.let { list ->
            showPokemonList(list)
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(
                R.id.goToPokemonListFragment
            )
        }

    }

    private fun showPokemonList(listHistory: List<Ability>) = with(binding) {
        adapter.setList(listHistory)
    }

    override fun onButtonClick(data: Ability) {

    }

}