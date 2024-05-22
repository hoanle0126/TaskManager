package com.example.taskmanagement.model

import java.time.LocalDate

class DateClass() {
    var day: Int = LocalDate.now().dayOfMonth
        private set

    var month: Int = LocalDate.now().monthValue
        private set

    var year: Int = LocalDate.now().year
         set
}