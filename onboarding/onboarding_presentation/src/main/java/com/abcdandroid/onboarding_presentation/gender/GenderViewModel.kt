package com.abcdandroid.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.model.Gender
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var currentGender: Gender by mutableStateOf(Gender.Male)
        private set

    private val _eventChannel = Channel<UiEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        currentGender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(currentGender)
            _eventChannel.send(UiEvent.Navigate(route = Route.AGE))
        }
    }
}