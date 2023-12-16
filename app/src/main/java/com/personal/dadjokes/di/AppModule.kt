package com.personal.dadjokes.di

import android.content.Context
import com.personal.dadjokes.data.remote.JokesApi
import com.personal.dadjokes.data.repository.JokesRepositoryImpl
import com.personal.dadjokes.domain.repository.JokesRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

interface AppModule {
    val jokesApi: JokesApi
    val jokesRepository: JokesRepository
}

class AppModuleImpl(private val appContext: Context): AppModule {

    override val jokesApi: JokesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    override val jokesRepository: JokesRepository by lazy {
        JokesRepositoryImpl(jokesApi)
    }
}