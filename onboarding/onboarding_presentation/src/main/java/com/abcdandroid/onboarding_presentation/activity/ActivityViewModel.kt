package com.abcdandroid.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.model.ActivityLevel
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var activityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val uiEventChannel = Channel<UiEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()


    fun onActivityLevelClick(activityLevel: ActivityLevel) {
        this.activityLevel = activityLevel
    }

    fun onNextClick() {
        preferences.saveActivityLevel(activityLevel)
        viewModelScope.launch {
            uiEventChannel.send(UiEvent.Navigate(Route.GOAL))
        }
    }


}