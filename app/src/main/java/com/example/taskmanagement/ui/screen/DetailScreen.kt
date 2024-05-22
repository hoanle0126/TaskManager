package com.example.taskmanagement.ui.screen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.taskmanagement.R
import com.example.taskmanagement.controller.AuthController
import com.google.firebase.auth.userProfileChangeRequest

@Composable
fun DetailScreen(navController: NavController) {
    var name by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.icon_add_outlined)
    var bitmap by remember {
        mutableStateOf(img)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = ""
        )
        Text(
            text = "Enter details account",
            style = MaterialTheme.typography.displayMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(Color.Blue),
                model = "",
                contentDescription = "",
            )
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Upload")
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            value = name,
            onValueChange = {name = it},
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            label = {
                Text(
                    text = "Name",
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
            onClick = { AuthController().updateDetail(name) }
        ) {
            Text(
                text = "Enter",
                style = MaterialTheme.typography.displayMedium,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Already have account? ",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .clickable { navController.navigate("Login") },
                text = "Login",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}