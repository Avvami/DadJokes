package com.personal.dadjokes.presentation.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.personal.dadjokes.R
import com.personal.dadjokes.domain.util.shareJoke
import com.personal.dadjokes.presentation.JokesState
import com.personal.dadjokes.presentation.UiEvent
import com.personal.dadjokes.presentation.ui.components.JokeBox
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurface
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
    uiEvent: (UiEvent) -> Unit,
    jokesState: () -> JokesState,
    currentDate: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Daily Dad Jokes")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = md_theme_light_onSurface,
                    navigationIconContentColor = md_theme_light_surface,
                    titleContentColor = md_theme_light_surface
                ),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.icon_menu_fill0), contentDescription = "Drawer")
                    }
                }
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_light_onSurface)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier
                .width(350.dp)
                .padding(16.dp)
                .padding(bottom = 64.dp)
            ) {
                Text(
                    text = "Here you go:",
                    color = md_theme_light_surface,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                JokeBox(
                    joke = { jokesState().jokesInfo?.jokes?.get(0)?.joke ?: "Sorry, no jokes today, an error has occurred" },
                    date = currentDate
                )
                Row(modifier = Modifier.align(Alignment.End)) {
                    IconButton(onClick = { uiEvent(UiEvent.GetJokes) }) {
                        AnimatedContent(
                            targetState = jokesState().isLoading,
                            label = "Renew animation",
                            transitionSpec = { scaleIn() togetherWith scaleOut() }
                        ) { isLoading ->
                            if (isLoading) {
                                CircularProgressIndicator(
                                    color = md_theme_light_surface,
                                    strokeWidth = 2.dp,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_autorenew_fill1),
                                    contentDescription = "Renew",
                                    tint = md_theme_light_surface
                                )
                            }
                        }
                    }
                    IconButton(onClick = {
                        jokesState().jokesInfo?.jokes?.get(0)?.joke?.let { joke ->
                            context.shareJoke(joke)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_share_fill1),
                            contentDescription = "Share",
                            tint = md_theme_light_surface
                        )
                    }
                }
            }
        }
    }
}