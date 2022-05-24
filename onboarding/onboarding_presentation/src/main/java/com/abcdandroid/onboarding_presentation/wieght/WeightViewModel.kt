package com.abcdandroid.onboarding_presentation.wieght

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.core.domain.preferences.Preferences
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
class WeightViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var weightString by mutableStateOf("80.0")
        private set

    private val _uiEventChannel = Channel<UiEvent>()
    val uiEventFlow = _uiEventChannel.receiveAsFlow()

    fun onWeightChange(weight: String) {
        if (weight.length <= 5) weightString = weight
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weight = weightString.toFloatOrNull() ?: kotlin.run {
                _uiEventChannel.send(UiEvent.ShowSnackBar(UiText.ResourceText(R.string.error_weight_cant_be_empty)))
                return@launch
            }
            preferences.saveWeight(weight)
            _uiEventChannel.send(UiEvent.Navigate(Route.ACTIVITY))

        }
    }

}