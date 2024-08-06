package com.example.joshsapplications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.joshsapplications.ui.theme.JoshsApplicationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoshsApplicationsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    var currentScreen by remember { mutableStateOf("login") }
                    when (currentScreen) {
                        "login" -> LoginScreen(navigateToHome = { currentScreen = "home" })
                        "create_account" -> CreateAccountScreen(navigateToLogin = { currentScreen = "login" })
                        "home" -> TodoScreen()
                    }
                }
            }
        }
    }
}
