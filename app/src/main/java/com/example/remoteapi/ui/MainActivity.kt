package com.example.remoteapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remoteapi.ui.screen.DetailsScreen
import com.example.remoteapi.ui.screen.HomeScreen
import com.example.remoteapi.ui.theme.RemoteApiTheme
import com.example.remoteapi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * API:
 * https://api.punkapi.com/v2/
 * */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RemoteApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen (
                                { navController.navigate("details") },
                                viewModel
                            )
                        }
                        composable("details"){
                            DetailsScreen()
                        }
                    }
                }
            }
        }
    }
}