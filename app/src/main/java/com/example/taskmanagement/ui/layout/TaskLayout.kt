package com.example.taskmanagement.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.taskmanagement.ui.component.BottomBar
import com.example.taskmanagement.ui.component.DrawerNavigation
import com.example.taskmanagement.ui.component.TaskTopBar
import com.example.taskmanagement.viewModel.UiViewModel

@Composable
fun TaskLayout(
    navController: NavHostController,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.zIndex(100f),
        drawerContent = { DrawerNavigation(navController,scope,drawerState) }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = { TaskTopBar(navController,scope,drawerState) },
            bottomBar = { BottomBar(navController) },

            ) {
            Layout(
                modifier = Modifier
                    .background(backgroundColor)
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