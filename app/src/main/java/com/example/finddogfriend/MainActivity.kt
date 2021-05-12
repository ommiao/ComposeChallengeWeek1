package com.example.finddogfriend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
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
                MainPage()
                DogDetail()
            }
        }
    }

    override fun onBackPressed() {
        if(viewModel.detailShowing){
            viewModel.closeDetail()
        } else {
            super.onBackPressed()
        }
    }

}