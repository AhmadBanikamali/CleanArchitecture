package com.abcdandroid.cleanarchitecture.navigation

import androidx.navigation.NavController
import com.abcdandroid.core.util.UiEvent

fun NavController.navigate(route: UiEvent.Navigate) {
    this.navigate(route.route)
}

