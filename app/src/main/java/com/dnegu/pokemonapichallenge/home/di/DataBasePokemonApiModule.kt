package com.dnegu.pokemonapichallenge.home.di

import android.content.Context
import androidx.room.Room
import com.dnegu.pokemonapichallenge.home.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBasePokemonApiModule {
    private const val DB_NAME = "pokemonApiDb.sqlite"
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()
    }

    @Provides
    fun provideLocalDao(database: AppDataBase) = database.pokemonListDao()
}