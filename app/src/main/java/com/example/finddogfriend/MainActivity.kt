package com.example.finddogfriend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finddogfriend.ui.DogDetail
import com.example.finddogfriend.ui.DogFriendViewModel
import com.example.finddogfriend.ui.MainPage
import com.example.finddogfriend.ui.theme.FindDogFriendTheme

class MainActivity : ComponentActivity() {

    val viewModel: DogFriendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FindDogFriendTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainPage(navController)}
                    composable("detail/{dogId}") { backStackEntry ->
                        DogDetail(backStackEntry.arguments?.getString("dogId"))}
                }
            }
        }
    }

}