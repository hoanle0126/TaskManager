package com.example.taskmanagement.data

import com.example.taskmanagement.R
import com.example.taskmanagement.model.IconButtonData

class ListIconButtonData {
    fun getAll(): List<IconButtonData> {
        return listOf<IconButtonData>(
            IconButtonData(
                R.drawable.icon_home_bold,
                "Home",
            ),
            IconButtonData(
                R.drawable.icon_profile_circle_bold,
                "Profile"
            ),
            IconButtonData(
                R.drawable.icon_add_circle_bold,
                "Add",
                true
            ),
            IconButtonData(
                R.drawable.icon_calendar_bold,
                "Calendar"
            ),
            IconButtonData(
                R.drawable.icon_bell_bold,
                "Alert"
            ),
        )
    }
}