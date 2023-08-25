package com.dnegu.pokemonapichallenge.home.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnegu.pokemonapichallenge.home.data.db.entity.PokemonListEntity

@Dao
interface PokemonListDao {

    @Query("select * from pokemon_list")
    suspend fun getAll(): List<PokemonListEntity>

    @Query("update pokemon_list set favorite =:favorite where name=:name")
    suspend fun changeFavorite(name : String, favorite: Int)

    @Query("select * from pokemon_list where favorite = 1 or favorite = 2 ")
    suspend fun getFavorites(): List<PokemonListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(vararg access: PokemonListEntity)
}