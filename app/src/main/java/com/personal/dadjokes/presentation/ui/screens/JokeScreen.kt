package com.personal.dadjokes.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.personal.dadjokes.R
import com.personal.dadjokes.presentation.JokesState
import com.personal.dadjokes.presentation.UiEvent
import com.personal.dadjokes.presentation.ui.components.JokeBox
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurface
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
    uiEvent: (UiEvent) -> Unit,
    jokesState: () -> JokesState
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
            ) {
                Text(
                    text = "Here you go:",
                    color = md_theme_light_surface,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                JokeBox(
                    joke = { jokesState().jokesInfo?.jokes?.get(0)?.joke ?: "Sorry, no jokes today, there is an error occurred" },
                    date = "shallow here"
                )
                Row(modifier = Modifier.align(Alignment.End)) {
                    IconButton(onClick = { uiEvent(UiEvent.GetJokes) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_autorenew_fill1),
                            contentDescription = "Renew",
                            tint = md_theme_light_surface
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
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