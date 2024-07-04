package com.example.joshsapplications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.example.joshsapplications.ui.theme.JoshsApplicationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoshsApplicationsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoScreen()
                }
            }
        }
    }
}
