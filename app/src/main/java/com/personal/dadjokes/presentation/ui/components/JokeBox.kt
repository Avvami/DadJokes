package com.personal.dadjokes.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.personal.dadjokes.R
import com.personal.dadjokes.presentation.ui.theme.coralPink
import com.personal.dadjokes.presentation.ui.theme.md_theme_light_onSurface

@Composable
fun JokeBox(
    modifier: Modifier = Modifier,
    joke: () -> String,
    date: String
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.extraLarge)
        .background(color = coralPink)
        .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.icon_format_quote_fill0),
                contentDescription = "Quote",
                tint = md_theme_light_onSurface.copy(alpha = .85f),
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = joke(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = md_theme_light_onSurface.copy(alpha = .85f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = md_theme_light_onSurface.copy(alpha = .85f)
            )
        }
    }
}