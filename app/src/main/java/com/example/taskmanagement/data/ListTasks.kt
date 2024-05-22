package com.example.taskmanagement.data

import androidx.compose.ui.graphics.Color
import com.example.taskmanagement.model.Task

class ListTasks {
    fun getAll(): List<Task> {
        return listOf<Task>(
            Task(
                "Redesign Main Page",
                Color.Red.copy(0.07f)
            ),
            Task(
                "Project Main Page",
                Color.Green.copy(0.07f)
            ),
            Task(
                "Travel",
                Color.Yellow.copy(0.07f)
            ),
        )
    }
}