
package com.example.motivocabmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import com.example.motivocabmobile.ui.MotivocabMobileApp
import com.example.motivocabmobile.ui.theme.MotivocabMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotivocabMobileTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MotivocabMobileApp()
                }
            }
        }
    }
}

