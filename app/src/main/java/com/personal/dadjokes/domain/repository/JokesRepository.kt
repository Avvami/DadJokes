package com.personal.dadjokes.domain.repository

import com.personal.dadjokes.domain.joke.JokesInfo
import com.personal.dadjokes.domain.util.Resource

interface JokesRepository {
    suspend fun getJokes(): Resource<JokesInfo>
}