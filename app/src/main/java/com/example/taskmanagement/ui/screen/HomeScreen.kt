package com.example.taskmanagement.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.taskmanagement.R
import com.example.taskmanagement.data.ListTasks
import com.example.taskmanagement.section.home_section.HelloSection
import com.example.taskmanagement.section.home_section.OngoingTaskSection

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        HelloSection()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            OngoingTaskSection()
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color.Black,
                            ambientColor = Color.Black,
                            shape = RoundedCornerShape(24.dp),
                        )
                        .fillMaxHeight()
                        .width(140.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(size = 16.dp)
                        ),
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Color.Cyan.copy(0.1f),
                                RoundedCornerShape(16.dp)
                            ),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column(
                            modifier = Modifier
                                .height(140.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp,Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Progress",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp),
                                contentAlignment = Alignment.Center
                            ){
                                CircularProgressIndicator(
                                    progress = { 0.4f },
                                    modifier = Modifier
                                        .shadow(
                                            elevation = 1.5.dp,
                                            spotColor = Color.Black,
                                            ambientColor = Color.Black,
                                            shape = RoundedCornerShape(99.dp),
                                        )
                                        .size(80.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    strokeWidth = 6.dp,
                                    trackColor = MaterialTheme.colorScheme.inverseSurface,
                                )
                                Text(
                                    text = "55%",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(
                                    Color.Gray.copy(0.2f)
                                )
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(13.dp),
                            verticalAlignment = Alignment.Bottom
                        ){
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "30%",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    text = "Last Week",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(bottom = 6.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(4.dp,Alignment.End)
                            ) {
                                listOf(32,16,28,24).forEach{
                                    Box(
                                        modifier = Modifier
                                            .width(8.dp)
                                            .height(it.dp)
                                            .background(
                                                MaterialTheme.colorScheme.onSurface,
                                                RoundedCornerShape(
                                                    topStart = 99.dp,
                                                    topEnd = 99.dp
                                                )
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically)
                ) {
                    listOf(
                        Color.Magenta.copy(0.07f),
                        Color.Yellow.copy(0.07f)
                    ).forEach{
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 4.dp,
                                    spotColor = Color.Black,
                                    ambientColor = Color.Black,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(
                                    MaterialTheme.colorScheme.surface
                                )
                                .fillMaxWidth()
                                .height(80.dp)
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .background(
                                        it
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "20\nProjects",
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.labelMedium
                                        )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(vertical = 20.dp),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp,Alignment.CenterHorizontally)
                                ) {
                                    listOf(100,24,54,80,60).forEach{
                                        Box(
                                            modifier = Modifier
                                                .fillMaxHeight(it.toFloat().div(100))
                                                .width(8.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.onSurface,
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
}