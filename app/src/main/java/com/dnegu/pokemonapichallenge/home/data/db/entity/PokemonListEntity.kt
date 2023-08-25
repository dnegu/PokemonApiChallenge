package com.dnegu.pokemonapichallenge.home.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_list")
data class PokemonListEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("pokemonId")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "favorite")
    val favorite: Int? = 0
)