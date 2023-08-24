package com.dnegu.pokemonapichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.dnegu.pokemonapichallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Pokemon API Challenge"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        with(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_pokemon_api) as NavHostFragment) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.pokemonEvolutionaryLineFragment,
                    R.id.pokemonSkillsFragment-> {

                    }
                }
            }
        }
    }
}