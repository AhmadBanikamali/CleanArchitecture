package com.abcdandroid.core.domain.use_case

import javax.inject.Inject

class FilterDigitUseCase @Inject constructor() {

    operator fun invoke(value: String): String {
        return value.filter { it.isDigit() }
    }

}