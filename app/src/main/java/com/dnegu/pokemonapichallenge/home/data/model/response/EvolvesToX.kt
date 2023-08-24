package com.dnegu.pokemonapichallenge.home.data.model.response

data class EvolvesToX(
    val evolution_details: List<EvolutionDetailX>,
    val evolves_to: List<Any>,
    val is_baby: Boolean,
    val species: SpeciesXX
)