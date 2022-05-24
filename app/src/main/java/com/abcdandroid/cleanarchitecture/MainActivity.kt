package com.abcdandroid.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.abcdandroid.cleanarchitecture.navigation.navigate
import com.abcdandroid.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.onboarding_presentation.activity.ActivityLevelScreen
import com.abcdandroid.onboarding_presentation.age.AgeScreen
import com.abcdandroid.onboarding_presentation.gender.GenderScreen
import com.abcdandroid.onboarding_presentation.goal.GoalTypeScreen
import com.abcdandroid.onboarding_presentation.height.HeightScreen
import com.abcdandroid.onboarding_presentation.welcome.WelcomeScreen
import com.abcdandroid.onboarding_presentation.wieght.WeightScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(route = Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.AGE) {
                            AgeScreen(navigate = navController::navigate, scaffoldState)
                        }
                        composable(route = Route.GENDER) {
                            GenderScreen(navigate = navController::navigate)
                        }
                        composable(route = Route.HEIGHT) {
                            HeightScreen(navigate = navController::navigate, scaffoldState)
                        }
                        composable(route = Route.WEIGHT) {
                            WeightScreen(navigate = navController::navigate, scaffoldState)
                        }
                        composable(route = Route.NUTRIENT_GOAL) {}
                        composable(route = Route.ACTIVITY) {
                            ActivityLevelScreen(navigate = navController::navigate)
                        }
                        composable(route = Route.GOAL) {
                            GoalTypeScreen(navigate = navController::navigate)
                        }
                        composable(route = Route.TRACKER_OVERVIEW) {}
                        composable(route = Route.SEARCH) {}
                    }

                }

            }
        }
    }
}
