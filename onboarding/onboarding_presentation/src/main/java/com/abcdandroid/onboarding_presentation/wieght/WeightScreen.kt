package com.abcdandroid.onboarding_presentation.wieght

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
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.core.R
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun WeightScreen(navigate: (UiEvent.Navigate) -> Unit, scaffoldState: ScaffoldState) {
    val viewModel = hiltViewModel<WeightViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEventFlow.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> navigate(uiEvent)
                is UiEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(uiEvent.uiText.asString(
                    context))
                else -> Unit
            }
        }
    })

    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceMedium)) {
        Column {
            Text(
                text = stringResource(id = R.string.whats_your_weight))
            Spacer(
                modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(
                value = viewModel.weightString,
                onValueChange = viewModel::onWeightChange,
                unit = stringResource(id = R.string.kg))
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd))
    }
}