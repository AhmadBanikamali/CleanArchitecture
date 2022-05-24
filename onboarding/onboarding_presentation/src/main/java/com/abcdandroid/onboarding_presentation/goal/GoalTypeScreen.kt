package com.abcdandroid.onboarding_presentation.goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.core.R
import com.abcdandroid.core.domain.model.GoalType
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GoalTypeScreen(navigate: (UiEvent.Navigate) -> Unit) {
    val viewModel = hiltViewModel<GoalTypeViewModel>()

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEventFlow.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navigate(event)
                else -> Unit
            }
        }
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceMedium), contentAlignment = Alignment.Center) {
        Column {
            Text(text = stringResource(id = R.string.your_goal))

            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))

            Row {

                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = viewModel.goalType.isSelected(GoalType.LoseWeight),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onGoalTypeSelect(GoalType.LoseWeight) })
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = viewModel.goalType.isSelected(GoalType.KeepWeight),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onGoalTypeSelect(GoalType.KeepWeight) })
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = viewModel.goalType.isSelected(GoalType.GainWeight),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onGoalTypeSelect(GoalType.GainWeight) })
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))

            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}