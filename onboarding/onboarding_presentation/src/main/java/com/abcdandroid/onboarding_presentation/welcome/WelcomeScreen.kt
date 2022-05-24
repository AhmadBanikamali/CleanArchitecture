package com.abcdandroid.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abcdandroid.core.R
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.SelectableButton
import com.abcdandroid.onboarding_presentation.components.UnitTextField


@Composable
fun WelcomeScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
        ActionButton(text = stringResource(id = R.string.next), onClick = {
            onNavigate(UiEvent.Navigate(Route.GENDER))
        }, isEnabled = true)
    }
}