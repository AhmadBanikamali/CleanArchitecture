package com.abcdandroid.core.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicText(val text: String) : UiText()
    data class ResourceText(@StringRes val textResId: Int) : UiText()


    fun asString(context: Context): String {
        return when (this) {
            is DynamicText -> text
            is ResourceText -> context.getString(textResId)
        }
    }


}