package com.marzbani.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.marzbani.presentation.navigation.AppNavHost
import com.marzbani.presentation.ui.theme.ImglyTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImglyTaskTheme {
                Surface {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }


    }
}

