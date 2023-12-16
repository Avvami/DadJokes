package com.personal.dadjokes.data.remote

import com.squareup.moshi.Json

data class JokesDto(
    @field:Json(name = "")
    val jokes: List<JokeDto>
)

data class JokeDto(
    val joke: String
)
