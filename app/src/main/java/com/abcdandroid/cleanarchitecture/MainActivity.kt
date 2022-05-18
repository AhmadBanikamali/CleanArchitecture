package com.abcdandroid.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abcdandroid.cleanarchitecture.navigation.navigate
import com.abcdandroid.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.abcdandroid.core.navigation.Route
import com.abcdandroid.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Route.WELCOME) {
                        composable(route = Route.WELCOME){
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.AGE){}
                        composable(route = Route.GENDER){}
                        composable(route = Route.HEIGHT){}
                        composable(route = Route.WEIGHT){}
                        composable(route = Route.NUTRIENT_GOAL){}
                        composable(route = Route.ACTIVITY){}
                        composable(route = Route.GOAL){}
                        composable(route = Route.TRACKER_OVERVIEW){}
                        composable(route = Route.SEARCH){}
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureTheme {
        Greeting("Android")
    }
}