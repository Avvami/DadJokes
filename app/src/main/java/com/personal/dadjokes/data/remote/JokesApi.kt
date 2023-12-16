package com.personal.dadjokes.data.remote

import com.personal.dadjokes.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header

interface JokesApi {
    @GET("v1/dadjokes?limit=1")
    suspend fun getJokes(
        @Header("X-Api-Key") apiKey: String = BuildConfig.API_KEY
    ): JokesDto
}