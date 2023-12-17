package com.personal.dadjokes.presentation.ui.screens

import android.app.TimePickerDialog
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.personal.dadjokes.R
import com.personal.dadjokes.domain.util.UiText
import com.personal.dadjokes.domain.util.shareJoke
import com.personal.dadjokes.presentation.JokesState
import com.personal.dadjokes.presentation.NotificationTimeState
import com.personal.dadjokes.presentation.UiEvent
import com.personal.dadjokes.presentation.ui.components.JokeBox
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurface
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurfaceVariant
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_surface
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_surfaceVariant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
    uiEvent: (UiEvent) -> Unit,
    jokesState: () -> JokesState,
    currentDate: String,
    notificationsEnabled: () -> Boolean,
    notificationTimeState: () -> NotificationTimeState
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            {_, hour: Int, minute: Int ->
                uiEvent(UiEvent.SetNotificationTime(hour, minute))
            },
            notificationTimeState().hour,
            notificationTimeState().minute,
            false
        )
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = md_theme_light_onSurface,
                drawerContentColor = md_theme_light_surface
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = md_theme_light_surface)
                        ) {
                            uiEvent(UiEvent.SetNotifications(!notificationsEnabled()))
                        }
                        .padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications"
                        )
                        Text(text = "Notifications", style = MaterialTheme.typography.titleMedium)
                    }
                    Switch(
                        checked = notificationsEnabled(),
                        onCheckedChange = { uiEvent(UiEvent.SetNotifications(it)) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = md_theme_light_onSurface,
                            checkedTrackColor = md_theme_light_surface,
                            uncheckedThumbColor = md_theme_light_onSurfaceVariant,
                            uncheckedTrackColor = md_theme_light_surface,
                            uncheckedBorderColor = md_theme_light_onSurfaceVariant
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = md_theme_light_surface)
                        ) {
                            timePickerDialog.show()
                        }
                        .padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_schedule_fill0),
                            contentDescription = "Notification time"
                        )
                        Text(text = "Notification time", style = MaterialTheme.typography.titleMedium)
                    }
                    TextButton(
                        onClick = { timePickerDialog.show() },
                        colors = ButtonDefaults.textButtonColors(contentColor = md_theme_light_surfaceVariant)
                    ) {
                        Text(
                            text = UiText.StringResource(
                                R.string.time,
                                notificationTimeState().hour,
                                notificationTimeState().minute
                            ).asString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = true
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
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Drawer")
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
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = md_theme_light_surface
                            )
                        }
                    }
                }
            }
        }
    }
}