package com.example.taskmanagement.ui.screen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanagement.R
import com.example.taskmanagement.viewModel.UiViewModel
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.time.Instant
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController) {
    var openStartTimeDialog by remember {
        mutableStateOf(false)
    }
    var openStartDatePickerState = rememberDatePickerState(
        yearRange = IntRange(LocalDate.now().year,2100),
    )
    var openEndTimeDialog by remember {
        mutableStateOf(false)
    }
    var openEndDatePickerState = rememberDatePickerState(
        yearRange = IntRange(LocalDate.now().year,2100),
        )
    var startTime by remember {
        mutableStateOf("")
    }
    var endTime by remember {
        mutableStateOf("")
    }
    var mainScrollState = rememberScrollState()
    var partScrollState = rememberScrollState()
    var colorTextField = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.outline.copy(0.6f),
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline.copy(0.6f),
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline.copy(0.6f),
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(mainScrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
            //        Task title
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(
                        text = "Color",
                        style = MaterialTheme.typography.titleMedium
                    )
                    HsvColorPicker(
                        modifier = Modifier
                            .size(60.dp),
                        initialColor = Color.White,
                        controller = rememberColorPickerController(),
                        onColorChanged = { colorEnvelope: ColorEnvelope ->
                            UiViewModel.instance = colorEnvelope.color.copy(0.1f)
                        },

                        )
                }
                Text(
                    text = "Task title",
                    style = MaterialTheme.typography.titleMedium
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    value = "",
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_task_outlined),
                            contentDescription = ""
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter task title",
                            style = MaterialTheme.typography.headlineLarge
                        )},
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.headlineLarge,
                    colors = colorTextField
                )
            }
//        Description
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    value = "",
                    maxLines = 3,
                    leadingIcon = {
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .padding(top = 15.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.icon_task_outlined),
                                contentDescription = ""
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Enter task title",
                            style = MaterialTheme.typography.headlineLarge
                        )},
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.headlineLarge,
                    colors = colorTextField
                )
            }
//        Deadline
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                //Start Time
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Start time",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        value = startTime,
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { openStartTimeDialog = true },
                                painter = painterResource(id = R.drawable.icon_task_outlined),
                                contentDescription = ""
                            )
                        },
                        placeholder = {
                            Text(
                                text = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                style = MaterialTheme.typography.headlineLarge
                            )},
                        readOnly = true,
                        onValueChange = {},
                        textStyle = MaterialTheme.typography.headlineLarge,
                        colors = colorTextField
                    )

                    if(openStartTimeDialog){
                        DatePickerDialog(
                            onDismissRequest = { openStartTimeDialog = false },
                            confirmButton = {
                                Button(onClick = {
                                    startTime = if(openStartDatePickerState.selectedDateMillis!=null){
                                        Instant
                                            .ofEpochMilli(openStartDatePickerState.selectedDateMillis!!)
                                            .atZone(ZoneId.of("UTC"))
                                            .toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                    } else { "" }
                                    openStartTimeDialog = false
                                }) {
                                    Text("Test")
                                }
                            }
                        ) {
                            DatePicker(openStartDatePickerState)
                        }
                    }
                }
                //End Time
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "End time",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        value = endTime,
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { openEndTimeDialog = true },
                                painter = painterResource(id = R.drawable.icon_task_outlined),
                                contentDescription = ""
                            )
                        },
                        placeholder = {
                            Text(
                                text = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                style = MaterialTheme.typography.headlineLarge
                            )},
                        readOnly = true,
                        onValueChange = {},
                        textStyle = MaterialTheme.typography.headlineLarge,
                        colors = colorTextField
                    )

                    if(openEndTimeDialog){
                        DatePickerDialog(
                            onDismissRequest = { openEndTimeDialog = false },
                            confirmButton = {
                                Button(onClick = {
                                    endTime = if(openEndDatePickerState.selectedDateMillis!=null){
                                        Instant
                                            .ofEpochMilli(openEndDatePickerState.selectedDateMillis!!)
                                            .atZone(ZoneId.of("UTC"))
                                            .toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                    } else { "" }
                                    openEndTimeDialog = false
                                }) {
                                    Text("Test")
                                }
                            }
                        ) {
                            DatePicker(openEndDatePickerState)
                        }
                    }
                }
            }

//        Participants
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Participants",
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.Start)
                ) {
                    Row(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHighest,
                                RoundedCornerShape(16.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(48.dp),
                            painter = painterResource(
                                id = R.drawable.icon_add_outlined),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .horizontalScroll(partScrollState),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        repeat(10){
                            Row(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surfaceContainerHighest,
                                        RoundedCornerShape(16.dp)
                                    )
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            Color.Black,
                                            RoundedCornerShape(99.dp)
                                        )
                                )
                                Text(
                                    text = "Johnson",
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = MaterialTheme.colorScheme.outline
                                )
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(RoundedCornerShape(99.dp))
                                        .clickable { }
                                    ,
                                    painter = painterResource(id = R.drawable.icon_cross),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                }
            }
            //        Task title
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Task title",
                    style = MaterialTheme.typography.titleMedium
                )
                repeat(5){
                    Row(
                        modifier = Modifier
                            .clickable {
                                navController.navigate("Add-Activity")
                            }
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_task_outlined),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = "Add activity",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
    }
}