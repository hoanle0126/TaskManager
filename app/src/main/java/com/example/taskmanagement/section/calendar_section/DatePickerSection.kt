package com.example.taskmanagement.section.calendar_section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagement.R
import com.example.taskmanagement.model.DateModel
import java.time.LocalDate
import java.time.Month
import java.time.Year

@Composable
fun DatePickerSection(dateModel: DateModel) {
    var selectedDay by remember {
        mutableStateOf(dateModel.day)
    }
    dateModel.day = selectedDay
    var selectedMonth by remember {
        mutableStateOf(dateModel.month)
    }
    dateModel.month = selectedMonth
    var selectedYear by remember {
        mutableStateOf(dateModel.year)
    }
    dateModel.year = selectedYear

    if( selectedDay > Month.of(selectedMonth).length(Year.of(selectedYear).isLeap) )
        selectedDay = Month.of(selectedMonth).length(Year.of(selectedYear).isLeap)

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            IconButton(onClick = { selectedYear-=1 }) {
                Icon(
                    modifier = Modifier
                        .rotate(180f),
                    painter = painterResource(id = R.drawable.icon_arrow_right_outlined),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "${dateModel.year}",
                style = MaterialTheme.typography.labelLarge
            )
            IconButton(onClick = { selectedYear +=1 }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_right_outlined),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            state = LazyListState(dateModel.month.plus(-1))
        ) {
            items(12){
                Text(
                    modifier = Modifier
                        .clickable {
                            selectedMonth = it.plus(1)
                        },
                    text = Month.of(it.plus(1)).toString().lowercase().replaceFirstChar {
                    it.uppercase()
                },
                    style = MaterialTheme.typography.labelLarge,
                    color = if(it.plus(1) == dateModel.month) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurface.copy(0.3f)
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            state = LazyListState(dateModel.day.plus(-1))
        ) {
            items(Month.of(dateModel.month).length(Year.of(dateModel.year).isLeap)){
                Column(
                    modifier = Modifier
                        .clickable {
                            selectedDay = it.plus(1)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (it.plus(1) == dateModel.day) MaterialTheme.colorScheme.onSurface
                                else MaterialTheme.colorScheme.onSurface.copy(0.3f),
                                RoundedCornerShape(99.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                        text = "${it.plus(1)}",
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.labelLarge
                    )
                    }
                    Text(
                        text = LocalDate.of(dateModel.year,1,it.plus(1)).dayOfWeek
                            .toString()
                            .lowercase()
                            .replaceFirstChar {
                                it.uppercase()
                            }
                            .take(3),
                        color = if(it.plus(1)==dateModel.day) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.onSurface.copy(0.3f),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}