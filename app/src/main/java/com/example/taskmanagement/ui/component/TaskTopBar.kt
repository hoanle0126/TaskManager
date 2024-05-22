package com.example.taskmanagement.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagement.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TaskTopBar(navController: NavController, scope: CoroutineScope, drawerState: DrawerState) {
    val navCurrentBackEntry by navController.currentBackStackEntryAsState()
    var nameRoute by remember {
        mutableStateOf("")
    }
    var doneAction by remember {
        mutableStateOf(false)
    }
    var backAction by remember {
        mutableStateOf(false)
    }
    when(navCurrentBackEntry?.destination?.route.toString()){
        "Home" -> {
            nameRoute = ""
            doneAction = false
            backAction = false
        }
        "Add" -> {
            nameRoute = "Create Task"
            doneAction = true
            backAction = true
        }
        "Calendar" -> {
            nameRoute = "Schedule's Task"
            doneAction = false
            backAction = false
        }
        else -> {
            nameRoute = navCurrentBackEntry?.destination?.route.toString()
            doneAction = false
            backAction = false
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = 10.dp,
                end = 10.dp
            )
            .heightIn(min = (48.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(R.drawable.icon_arrow_left),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = if (navCurrentBackEntry?.destination?.route != null) {
                nameRoute
            } else "",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
        if(!doneAction){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.icon_setting_bold),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }else{
            Column(
                modifier = Modifier
                    .width(50.dp)
                    .height(24.dp)
                    .background(
                        MaterialTheme.colorScheme.onSurface,
                        RoundedCornerShape(8.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}