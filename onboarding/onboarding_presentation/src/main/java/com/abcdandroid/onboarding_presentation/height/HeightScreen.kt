package com.abcdandroid.onboarding_presentation.height

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core.util.UiText
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.core.R
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun HeightScreen(navigate: (UiEvent.Navigate) -> Unit, scaffoldState: ScaffoldState) {
    val viewModel = hiltViewModel<HeightViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEventChannel.collect {
            when (it) {
                is UiEvent.Navigate -> navigate(it)
                is UiEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(it.uiText.asString(
                    context))
                else -> Unit
            }
        }
    })

    Box(modifier = Modifier
        .padding(LocalSpacing.current.spaceMedium)
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column {
            Text(text = stringResource(id = R.string.whats_your_height))
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(value = viewModel.heightString,
                onValueChange = viewModel::onHeightChange,
                unit = stringResource(id = R.string.cm))
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick
        )
    }


}