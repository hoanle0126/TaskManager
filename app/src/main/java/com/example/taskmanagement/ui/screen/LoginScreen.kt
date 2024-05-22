package com.example.taskmanagement.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanagement.R
import com.example.taskmanagement.controller.AuthController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    lateinit var auth: FirebaseAuth
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp,Alignment.CenterVertically)
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = ""
        )
        Text(
            text = "Login to app",
            style = MaterialTheme.typography.displayMedium
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            value = email,
            onValueChange = {email = it},
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp)),
            shape = RoundedCornerShape(12.dp),
            value = password,
            onValueChange = {password = it},
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyMedium,
            label = {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.surface
            ),
            onClick = { AuthController().login(email,password) }
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayMedium,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Don't have account? ",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .clickable { navController.navigate("Signup") },
                text = "Sign up",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}