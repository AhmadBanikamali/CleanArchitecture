package com.abcdandroid.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.domain.use_case.FilterDigitUseCase
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core.util.UiText
import com.abcdandroid.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitUseCase: FilterDigitUseCase,
) : ViewModel() {

    var ageString by mutableStateOf(preferences.loadUserInfo().age.toString())
        private set

    private val _ageChannel = Channel<UiEvent>()
    val ageChannel = _ageChannel.receiveAsFlow()

    fun onAgeChange(age: String) {
        if (age.length <= 3)
            this.ageString = filterDigitUseCase(age)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val age = ageString.toIntOrNull() ?: run {
                _ageChannel.send(UiEvent.ShowSnackBar(uiText = UiText.ResourceText(R.string.error_age_cant_be_empty)))
                return@launch
            }

            preferences.saveAge(age)
            viewModelScope.launch {
                _ageChannel.send(UiEvent.Navigate(route = Route.HEIGHT))
            }

        }
    }

}