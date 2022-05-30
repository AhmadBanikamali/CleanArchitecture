package com.abcdandroid.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.domain.use_case.FilterDigitUseCase
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.onboarding_domin.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val digitUseCase: FilterDigitUseCase,
    private val validateNutrients: ValidateNutrients,
) : ViewModel() {

    private val uiChannel = Channel<UiEvent>()
    val uiFlow = uiChannel.receiveAsFlow()

    var nutrientGoalState by mutableStateOf(NutrientGoalState())

    fun onEvent(nutrientGoalEvent: NutrientGoalEvent) {
        when (nutrientGoalEvent) {
            is NutrientGoalEvent.OnCarbValueChange -> nutrientGoalState = nutrientGoalState.copy(carbs = digitUseCase(nutrientGoalEvent.value))
            is NutrientGoalEvent.OnFatValueChange -> nutrientGoalState = nutrientGoalState.copy(fats = digitUseCase(nutrientGoalEvent.value))
            is NutrientGoalEvent.OnProteinValueChange -> nutrientGoalState = nutrientGoalState.copy(proteins = digitUseCase(nutrientGoalEvent.value))
            is NutrientGoalEvent.OnNextClick -> viewModelScope.launch {
                validateNutrients(
                    nutrientGoalState.carbs,
                    nutrientGoalState.proteins,
                    nutrientGoalState.fats,
                ).apply {
                    when (this) {
                        is ValidateNutrients.Result.Failure -> uiChannel.send(UiEvent.ShowSnackBar(errorMessage))
                        is ValidateNutrients.Result.Success -> {
                            preferences.saveFatRatio(fat)
                            preferences.saveCarbRatio(carbs)
                            preferences.saveProteinRatio(protein)
                            uiChannel.send(UiEvent.Navigate(route = Route.TRACKER_OVERVIEW))
                        }
                    }
                }
            }
        }
    }


}