package com.example.taskmanagement.section.home_section

import android.app.Dialog
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.taskmanagement.R
import com.example.taskmanagement.data.ListTasks

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OngoingTaskSection() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    var openPopup by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(188.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(33.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "Ongoing Task",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .horizontalScroll(rememberScrollState())
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
//                    Ongoing Task Section
                ListTasks().getAll().forEach{
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(120.dp)
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color.Black,
                                ambientColor = Color.Black,
                                shape = RoundedCornerShape(24.dp),
                            )
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(size = 24.dp)
                            ),
                    ){
                        if(ListTasks().getAll().indexOf(it) == selectedIndex){
                            if(openPopup){
                                Popup(
                                    alignment = Alignment.TopEnd,
                                    offset = IntOffset(
                                        -20,
                                        80
                                    ),
                                    properties = PopupProperties(
                                        usePlatformDefaultWidth = true
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .shadow(
                                                elevation = 2.dp,
                                                spotColor = Color.Black,
                                                ambientColor = Color.Black,
                                                shape = RoundedCornerShape(8.dp),
                                            )
                                            .background(
                                                MaterialTheme.colorScheme.surface,
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(vertical = 10.dp)
                                            .width(140.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .clickable {  }
                                                .fillMaxWidth()
                                                .padding(horizontal = 10.dp)
                                                .height(30.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "View details",
                                                style = MaterialTheme.typography.headlineLarge
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(it.backgroundColor)
                                .padding(15.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start,
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(0.8f)
                                        .fillMaxHeight(),
                                    text = it.name,
                                    fontSize = 14.sp,
                                    minLines = 2,
                                    maxLines = 2,
                                    lineHeight = 18.sp,
                                )
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            selectedIndex = ListTasks().getAll().indexOf(it)
                                            openPopup = !openPopup
                                        }
                                        .weight(0.2f),
                                    painter = painterResource(id = R.drawable.icon_more),
                                    contentDescription = ""
                                )
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
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.spacedBy(-8.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    listOf(
                                        "","","","",""
                                    ).subList(0,2).forEach {
                                        AsyncImage(
                                            modifier = Modifier
                                                .shadow(
                                                    elevation = 4.dp,
                                                    spotColor = Color.Black,
                                                    ambientColor = Color.Black,
                                                    shape = RoundedCornerShape(99.dp)
                                                )
                                                .size(24.dp)
                                                .background(
                                                    Color.Red,
                                                    RoundedCornerShape(99.dp)
                                                )
                                                .clip(RoundedCornerShape(99.dp)),
                                            model = "https://www.timeforkids.com/wp-content/uploads/2023/11/G3G5_231117_bear_steps.jpg?w=1024",
                                            contentDescription = "",
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .shadow(
                                                elevation = 4.dp,
                                                spotColor = Color.Black,
                                                ambientColor = Color.Black,
                                                shape = RoundedCornerShape(99.dp)
                                            )
                                            .size(24.dp)
                                            .background(
                                                MaterialTheme.colorScheme.surfaceTint,
                                                RoundedCornerShape(99.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ){
                                        Text(
                                            text = "+${2}",
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(12.dp),
                                            painter = painterResource(id = R.drawable.icon_home_bold),
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.outline.copy(0.7f)
                                        )
                                        Text(
                                            text = "9:30 AM",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.outline.copy(0.7f)
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(12.dp),
                                            painter = painterResource(id = R.drawable.icon_home_bold),
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.outline.copy(0.7f)
                                        )
                                        Text(
                                            text = "9:30 AM",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.outline.copy(0.7f)
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