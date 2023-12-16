package com.personal.dadjokes.data.repository

import com.personal.dadjokes.data.mappers.toJokesInfo
import com.personal.dadjokes.data.remote.JokesApi
import com.personal.dadjokes.domain.joke.JokesInfo
import com.personal.dadjokes.domain.repository.JokesRepository
import com.personal.dadjokes.domain.util.Resource

class JokesRepositoryImpl(
    private val api: JokesApi
):JokesRepository {
    override suspend fun getJokes(): Resource<JokesInfo> {
        return try {
            Resource.Success(
                data = api.getJokes().toJokesInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}