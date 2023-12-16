package com.personal.dadjokes.data.mappers

import com.personal.dadjokes.data.remote.JokeDto
import com.personal.dadjokes.data.remote.JokesDto
import com.personal.dadjokes.domain.joke.Joke
import com.personal.dadjokes.domain.joke.JokesInfo

fun JokesDto.toJokesInfo(): JokesInfo {
    return JokesInfo(
        jokes = jokes.map {
            it.toJoke()
        }
    )
}

fun JokeDto.toJoke(): Joke {
    return Joke(joke = joke)
}