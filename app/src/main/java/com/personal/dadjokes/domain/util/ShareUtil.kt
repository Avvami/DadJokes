package com.personal.dadjokes.domain.util

import android.content.Context
import android.content.Intent

fun Context.shareJoke(joke: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, joke)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}