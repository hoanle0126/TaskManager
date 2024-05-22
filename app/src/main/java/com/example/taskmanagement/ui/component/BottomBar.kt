package com.example.taskmanagement.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagement.R
import com.example.taskmanagement.data.ListIconButtonData
import java.time.format.TextStyle

@Composable
fun BottomBar(navController: NavController) {
    val navCurrentBackEntry by navController.currentBackStackEntryAsState()

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        containerColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color.Black,
                    ambientColor = Color.Black,
                    shape = RoundedCornerShape(99.dp),
                )
                .fillMaxWidth()
                .height(52.dp)
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = RoundedCornerShape(size = 99.dp)
                )
                .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            ListIconButtonData().getAll().forEach{
                if(it.main){
                    Column(
                        modifier = Modifier
                            .width(48.dp)
                            .fillMaxHeight()
                            .clickable {

                                if(navCurrentBackEntry?.destination?.route.toString()==it.description)
                                {}
                                else navController.navigate(it.description)

                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_add_circle_bold),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                else {
                    Column(
                        modifier = Modifier
                            .width(40.dp)
                            .fillMaxHeight()
                            .clickable {

                                if(navCurrentBackEntry?.destination?.route.toString()==it.description)
                                {}
                                else navController.navigate(it.description)

                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    if(navCurrentBackEntry?.destination?.route.toString()==it.description)
                                        MaterialTheme.colorScheme.onBackground
                                    else Color.Transparent,
                                    RoundedCornerShape(99.dp)
                                )
                        )
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = it.description,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 4.dp),
                            text = it.description,
                            fontSize = 8.sp,
                            fontWeight = FontWeight(500),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}