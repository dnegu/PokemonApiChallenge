package com.dnegu.pokemonapichallenge.home.di

import com.dnegu.pokemonapichallenge.BuildConfig
import com.dnegu.pokemonapichallenge.home.data.repositories.IPokemonRepository
import com.dnegu.pokemonapichallenge.home.data.repositories.PokemonRepository
import com.dnegu.pokemonapichallenge.home.data.services.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonApiModule {
    val BASE_URL = "https://pokeapi.co/api/v2/"
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val httpClient = httpClientBuilder.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCoursesRepository(apiService: PokemonApiService): IPokemonRepository {
        return PokemonRepository(apiService)
    }
}