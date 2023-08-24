package com.dnegu.pokemonapichallenge.home.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnegu.pokemonapichallenge.R
import com.dnegu.pokemonapichallenge.home.viewmodel.PokemonSkillsViewModel

class PokemonSkillsFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonSkillsFragment()
    }

    private lateinit var viewModel: PokemonSkillsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_skills, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonSkillsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}