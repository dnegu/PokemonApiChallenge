package com.dnegu.pokemonapichallenge.home.data.model.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonList>
)