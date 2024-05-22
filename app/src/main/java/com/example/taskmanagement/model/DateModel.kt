package com.example.taskmanagement.model

data class DateModel (
    var day:Int,
    var month: Int,
    var year:Int
){
    var test_day = day
        set

    var _month = month
        set

    var test_year = year
        set
}