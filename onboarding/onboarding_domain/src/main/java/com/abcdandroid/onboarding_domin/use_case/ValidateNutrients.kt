package com.abcdandroid.onboarding_domin.use_case

import com.abcdandroid.core.util.UiText
import com.abcdandroid.core.R
import javax.inject.Inject


class ValidateNutrients @Inject constructor() {

    operator fun invoke(carb: String, protein: String, fat: String): Result {

        val carbsInt = carb.toIntOrNull()
        val proteinInt = protein.toIntOrNull()
        val fatInt = fat.toIntOrNull()

        if (carbsInt == null || proteinInt == null || fatInt == null) {
            return Result.Failure(UiText.ResourceText(R.string.error_invalid_values))
        }

        if (carbsInt + proteinInt + fatInt != 100) return Result.Failure(UiText.ResourceText(R.string.error_not_100_percent))

        return Result.Success(carbsInt/100f, proteinInt/100f, fatInt/100f)

    }


    sealed class Result {
        data class Success(val carbs: Float, val protein: Float, val fat: Float) : Result()
        data class Failure(val errorMessage: UiText) : Result()
    }

}