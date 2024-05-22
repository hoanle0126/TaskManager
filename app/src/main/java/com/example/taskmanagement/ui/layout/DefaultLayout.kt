package com.example.taskmanagement.ui.layout

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagement.R
import com.example.taskmanagement.ui.component.BottomBar
import com.example.taskmanagement.ui.component.DrawerNavigation
import com.example.taskmanagement.ui.component.TopBar
import com.example.taskmanagement.ui.screen.AddScreen
import com.example.taskmanagement.ui.screen.AlertScreen
import com.example.taskmanagement.ui.screen.CalendarScreen
import com.example.taskmanagement.ui.screen.HomeScreen
import com.example.taskmanagement.ui.screen.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultLayout(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val items = listOf(Icons.Default.Close, Icons.Default.CheckCircle, Icons.Default.Call)
        val selectedItem = remember { mutableStateOf(items[0]) }
        ModalNavigationDrawer(
            drawerState = drawerState,
            modifier = Modifier.zIndex(100f),
            drawerContent = { DrawerNavigation(navController,scope,drawerState) }
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = { TopBar(navController,scope,drawerState) },
                bottomBar = { BottomBar(navController) },

            ) {
                Layout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    content = content
                ) { measurables, constraints ->
                    // Don't constrain child views further, measure them with given constraints
                    // List of measured children
                    val placeables = measurables.map { measurable ->
                        // Measure each children
                        measurable.measure(constraints)
                    }

                    // Set the size of the layout as big as it can
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        // Track the y co-ord we have placed children up to
                        var yPosition = 0

                        // Place children in the parent layout
                        placeables.forEach { placeable ->
                            // Position item on the screen
                            placeable.placeRelative(x = 0, y = yPosition)

                            // Record the y co-ord placed up to
                            yPosition += placeable.height
                        }
                    }
                }
            }
        }
}