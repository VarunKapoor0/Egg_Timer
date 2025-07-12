package com.varun.egg_timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.varun.egg_timer.ui.EggTimerScreen.EggTimerScreen
import com.varun.egg_timer.ui.Welcome.WelcomeScreen
import com.varun.egg_timer.ui.theme.Egg_TimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Egg_TimerTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "welcome"){
                    composable("welcome") { WelcomeScreen(onStartClick = {navController.navigate("timer") })}
                    composable("timer") {EggTimerScreen()}
                }

            }
        }
    }
}