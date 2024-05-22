package com.example.taskmanagement.model

import androidx.annotation.DrawableRes

data class IconButtonData(
    @DrawableRes val icon:Int,
    val description: String,
    val main: Boolean = false,
    @DrawableRes val iconOutline:Int = 0
)
