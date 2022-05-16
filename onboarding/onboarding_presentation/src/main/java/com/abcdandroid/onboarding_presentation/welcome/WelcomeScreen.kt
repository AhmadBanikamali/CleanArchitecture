package com.abcdandroid.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abcdandroid.core.R
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.onboarding_presentation.components.ActionButton


@Composable
fun WelcomeScreen() {
    Column(Modifier.fillMaxSize().padding(LocalSpacing.current.spaceMedium), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.welcome_text), style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
        ActionButton(text = stringResource(id = R.string.next), onClick = {

        }, isEnabled = true)
    }
}