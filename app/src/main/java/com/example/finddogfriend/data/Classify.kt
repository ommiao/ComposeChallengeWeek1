package com.example.finddogfriend.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Classify(val name: String) {
    var selected: Boolean by mutableStateOf(false)
}