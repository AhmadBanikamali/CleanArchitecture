package com.abcdandroid.onboarding_presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcdandroid.core.R
import com.abcdandroid.core.domain.model.ActivityLevel
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun ActivityLevelScreen(navigate: (UiEvent.Navigate) -> Unit) {
    val viewModel = hiltViewModel<ActivityViewModel>()

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEventFlow.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navigate(event)
                else -> Unit
            }
        }
    })


    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceMedium)) {
        Column {
            Text(text = stringResource(id = R.string.whats_your_activity_level))
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            Row() {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = viewModel.activityLevel.isSelected(ActivityLevel.Low),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.Low) }
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewModel.activityLevel.isSelected(ActivityLevel.Medium),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.Medium) }
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewModel.activityLevel.isSelected(ActivityLevel.High),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.High) }
                )
            }

        }

        ActionButton(
            modifier = Modifier.align(
                Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
        )
    }
}
