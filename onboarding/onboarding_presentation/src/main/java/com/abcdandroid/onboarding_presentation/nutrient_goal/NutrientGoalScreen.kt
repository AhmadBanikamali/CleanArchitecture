package com.abcdandroid.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcdandroid.core.R
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    navigate: (UiEvent.Navigate) -> Unit,
) {
    val viewModel: NutrientGoalViewModel = hiltViewModel()

    val context = LocalContext.current
    LaunchedEffect(key1 = true, block = {
        viewModel.uiFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> navigate(uiEvent)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(uiEvent.uiText.asString(context))
                }
                else -> Unit
            }
        }
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceMedium), contentAlignment = Alignment.Center) {
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.onEvent(NutrientGoalEvent.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.what_are_your_nutrient_goals))
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(
                value = viewModel.nutrientGoalState.carbs,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnCarbValueChange(it)) },
                unit = stringResource(id = R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(
                value = viewModel.nutrientGoalState.proteins,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnProteinValueChange(it)) },
                unit = stringResource(id = R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(
                value = viewModel.nutrientGoalState.fats,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnFatValueChange(it)) },
                unit = stringResource(id = R.string.percent_fats)
            )
        }


    }
}