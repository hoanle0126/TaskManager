package com.example.taskmanagement.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskmanagement.R
import com.example.taskmanagement.model.DateModel
import com.example.taskmanagement.section.calendar_section.DatePickerSection
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    var dateModel: DateModel = DateModel(
            day = LocalDate.now().dayOfMonth,
            month = LocalDate.now().monthValue,
            year = LocalDate.now().year
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePickerSection(dateModel)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            repeat(12){
                Row(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .width(70.dp)
                            .height(80.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(
                                    Color.Gray.copy(0.3f)
                                )
                        )
                        Text(
                            text = "15:00\nPM",
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.End
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .shadow(
                                elevation = 6.dp,
                                ambientColor = Color.Black,
                                spotColor = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .fillMaxSize()
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .background(
                                    Color.Red.copy(0.07f),
                                    RoundedCornerShape(24.dp)
                                )
                                .padding(15.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.65f),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Design System",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "09:10-10:00",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.outline.copy(0.6f)
                                    )
                                }
                                Column {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .height(10.dp)
                                            .clip(RoundedCornerShape(99.dp)),
                                        progress = 0.6f
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Progress",
                                            color = Color.Gray.copy(0.6f),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                        Text(
                                            text = "60%",
                                            color = Color.Gray.copy(0.6f),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.End
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {  },
                                    painter = painterResource(id = R.drawable.icon_more),
                                    contentDescription = "",
                                    tint = Color.Gray.copy(0.8f)
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy((-8).dp,Alignment.End)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(
                                                Color.Red,
                                                RoundedCornerShape(99.dp)
                                            )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(
                                                Color.Red,
                                                RoundedCornerShape(99.dp)
                                            )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(
                                                Color.Red,
                                                RoundedCornerShape(99.dp)
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}