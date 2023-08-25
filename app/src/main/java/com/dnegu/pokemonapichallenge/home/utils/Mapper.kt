package com.dnegu.pokemonapichallenge.home.utils

import com.dnegu.pokemonapichallenge.home.data.db.entity.PokemonListEntity
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList

fun PokemonListEntity.toModel() = PokemonList(
    name = this.name,
    url = this.url,
    favorite = this.favorite
)

fun PokemonList.toEntity() = PokemonListEntity(
    id = 0,
    name = this.name,
    url = this.url,
    favorite = this.favorite
)