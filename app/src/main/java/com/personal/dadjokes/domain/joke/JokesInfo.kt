package com.personal.dadjokes.domain.joke

data class JokesInfo(
    val jokes: List<Joke>
)

data class Joke(
    val joke: String
)
