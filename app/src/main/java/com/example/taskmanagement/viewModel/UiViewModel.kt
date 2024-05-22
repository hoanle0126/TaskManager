package com.example.taskmanagement.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class UiViewModel:ViewModel() {
    companion object {
        private var backgroundTaskColor:Color by mutableStateOf(Color.Transparent)

        var instance:Color
            get() {
                return backgroundTaskColor!!
            }

            set(value) { backgroundTaskColor = value }
    }
}