package com.abcdandroid.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.model.GoalType
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalTypeViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var goalType by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val uiEventChannel = Channel<UiEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()

    fun onGoalTypeSelect(goalType: GoalType) {
        this.goalType = goalType
    }

    fun onNextClick() {
        preferences.saveGoalType(goalType)
        viewModelScope.launch {
            uiEventChannel.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }


}