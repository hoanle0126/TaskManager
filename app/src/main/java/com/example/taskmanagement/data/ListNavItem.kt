package com.example.taskmanagement.data

import com.example.taskmanagement.R
import com.example.taskmanagement.model.IconButtonData

class ListNavItem {
    fun getAll(): List<IconButtonData> {
        return listOf<IconButtonData>(
            IconButtonData(
                R.drawable.icon_home_bold,
                "Home",
                iconOutline = R.drawable.icon_home_outlined
            ),
            IconButtonData(
                R.drawable.icon_task_bold,
                "Task List",
                iconOutline = R.drawable.icon_task_outlined
            ),
        )
    }
}