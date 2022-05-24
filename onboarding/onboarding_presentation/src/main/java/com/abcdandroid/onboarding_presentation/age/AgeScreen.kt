package com.abcdandroid.onboarding_presentation.age

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
import com.abcdandroid.core_ui.LocalSpacing
import com.abcdandroid.core.R
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.onboarding_presentation.components.ActionButton
import com.abcdandroid.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun AgeScreen(navigate: (UiEvent.Navigate) -> Unit, scaffoldState: ScaffoldState) {
    val ageViewModel: AgeViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        ageViewModel.ageChannel.collect {
            if (it is UiEvent.Navigate) navigate(it)
            if (it is UiEvent.ShowSnackBar)
                scaffoldState.snackbarHostState.showSnackbar(it.uiText.asString(context))
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceMedium),
        contentAlignment = Alignment.Center
    ) {
        Column {

            Text(text = stringResource(id = R.string.whats_your_age))

            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))

            UnitTextField(
                value = ageViewModel.ageString,
                onValueChange = { ageViewModel.onAgeChange(it) },
                unit = stringResource(
                    id = R.string.years
                )
            )

        }

        ActionButton(text = stringResource(id = R.string.next),
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                ageViewModel.onNextClick()
            })
    }

}