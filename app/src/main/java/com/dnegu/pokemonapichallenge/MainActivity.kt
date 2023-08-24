package com.dnegu.pokemonapichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}