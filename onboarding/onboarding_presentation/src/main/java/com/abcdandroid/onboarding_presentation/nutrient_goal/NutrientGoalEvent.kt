package com.abcdandroid.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    object OnNextClick : NutrientGoalEvent()
    data class OnFatValueChange(val value: String) : NutrientGoalEvent()
    data class OnCarbValueChange(val value: String) : NutrientGoalEvent()
    data class OnProteinValueChange(val value: String) : NutrientGoalEvent()
}
