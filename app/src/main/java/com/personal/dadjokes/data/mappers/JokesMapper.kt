package com.personal.dadjokes.data.mappers

import com.personal.dadjokes.data.remote.JokeDto
import com.personal.dadjokes.domain.joke.Joke
import com.personal.dadjokes.domain.joke.JokesInfo

fun List<JokeDto>.toJokesInfo(): JokesInfo {
    return JokesInfo(
        jokes = this.map {
            it.toJoke()
        }
    )
}

fun JokeDto.toJoke(): Joke {
    return Joke(joke = joke)
}