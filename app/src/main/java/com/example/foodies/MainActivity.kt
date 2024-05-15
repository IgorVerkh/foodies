package com.example.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.foodies.ui.navigation.SetupNavGraph
import com.example.foodies.ui.theme.FoodiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    FoodiesTheme(
        darkTheme = false
    ) {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}