package com.personal.dadjokes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.personal.dadjokes.DadJokesApplication
import com.personal.dadjokes.presentation.ui.screens.JokeScreen
import com.personal.dadjokes.presentation.ui.theme.DadJokesTheme
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurface

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory {
            MainViewModel(DadJokesApplication.appModule.jokesRepository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DadJokesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = md_theme_light_onSurface
                ) {
                    JokeScreen(
                        uiEvent = mainViewModel::uiEvent,
                        jokesState = mainViewModel::jokesState,
                        currentDate = mainViewModel.currentDate
                    )
                }
            }
        }
    }
}