package com.abcdandroid.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel("low")
    object Medium: ActivityLevel("medium")
    object High: ActivityLevel("high")

    fun isSelected(activityLevel: ActivityLevel): Boolean {
        return this==activityLevel
    }

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when(name) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium
            }
        }
    }
}
