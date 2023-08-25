package com.dnegu.pokemonapichallenge.home.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dnegu.pokemonapichallenge.home.data.db.dao.PokemonListDao
import com.dnegu.pokemonapichallenge.home.data.db.entity.PokemonListEntity

@Database(
    entities = [
        PokemonListEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonListDao(): PokemonListDao
}