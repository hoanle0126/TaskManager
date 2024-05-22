package com.example.taskmanagement.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagement.ui.layout.DefaultLayout
import com.example.taskmanagement.ui.layout.TaskLayout
import com.example.taskmanagement.ui.screen.AddActivityScreen
import com.example.taskmanagement.ui.screen.AddScreen
import com.example.taskmanagement.ui.screen.AlertScreen
import com.example.taskmanagement.ui.screen.CalendarScreen
import com.example.taskmanagement.ui.screen.DetailScreen
import com.example.taskmanagement.ui.screen.HomeScreen
import com.example.taskmanagement.ui.screen.LoginScreen
import com.example.taskmanagement.ui.screen.ProfileScreen
import com.example.taskmanagement.ui.screen.SignupScreen
import com.example.taskmanagement.ui.screen.TaskListScreen
import com.example.taskmanagement.ui.screen.WelcomeScreen
import com.example.taskmanagement.viewModel.AuthViewModel
import com.example.taskmanagement.viewModel.UiViewModel

@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = if(AuthViewModel().auth.currentUser!=null){
                "Home"
            } else "Welcome"
        ) {
            composable("Welcome"){
                WelcomeScreen(navController)
            }
            composable("Login"){
                LoginScreen(navController)
            }
            composable("Signup"){
                SignupScreen(navController)
            }
            composable("Detail"){
                DetailScreen(navController)
            }
            composable(
                "Home",
            ) {
                DefaultLayout(navController = navController){
                    HomeScreen()
                }
            }
            composable(
                "Calendar",
            ) {
                DefaultLayout(navController = navController){
                    CalendarScreen()
                }
            }
            composable(
                "Add",
            ) {
                TaskLayout(
                    navController = navController,
                    backgroundColor = UiViewModel.instance
                ){
                    AddScreen(navController)
                }
            }
            composable(
                "Add-Activity",
            ) {
                TaskLayout(
                    navController = navController,
                    backgroundColor = UiViewModel.instance
                ){
                    AddActivityScreen(navController)
                }
            }
            composable(
                "Profile",
            ) {
                DefaultLayout(navController = navController){
                    ProfileScreen()
                }
            }
            composable(
                "Alert",
            ) {
                DefaultLayout(navController = navController){
                    AlertScreen()
                }
            }
            composable(
                "Task-List"
            ) {
                TaskLayout(navController = navController){
                    TaskListScreen()
                }
            }
        }
}