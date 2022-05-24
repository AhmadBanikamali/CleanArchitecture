package com.abcdandroid.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.preferences.Preferences
import com.abcdandroid.core.domain.use_case.FilterDigitUseCase
import com.abcdandroid.core.util.UiEvent
import com.abcdandroid.core.util.UiText
import com.abcdandroid.core.R
import com.abcdandroid.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitUseCase: FilterDigitUseCase,
) : ViewModel() {

    var heightString by mutableStateOf("180")
    private set
    private val _uiEventChannel = Channel<UiEvent>()
    val uiEventChannel = _uiEventChannel.receiveAsFlow()

    fun onHeightChange(height: String) {
        if (height.length <= 3)
            heightString = filterDigitUseCase(height)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val height = heightString.toIntOrNull() ?: kotlin.run {
                _uiEventChannel.send(UiEvent.ShowSnackBar(UiText.ResourceText(R.string.error_height_cant_be_empty)))
                return@launch
            }
            preferences.saveHeight(height)
            _uiEventChannel.send(UiEvent.Navigate(route = Route.WEIGHT))
        }
    }

}