package com.example.taskmanagement.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskmanagement.R
import com.example.taskmanagement.viewModel.AuthViewModel

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(99.dp)),
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .clickable { }
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(99.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier
                        .size(21.dp),
                    painter = painterResource(id = R.drawable.icon_edit_2),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.outline
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = AuthViewModel().auth.currentUser?.displayName.toString(),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = AuthViewModel().auth.currentUser?.email.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline.copy(0.6f)
        )
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "About me",
                style = MaterialTheme.typography.titleMedium
                )
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                        " when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline.copy(0.6f)
                )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            listOf(
                1,2,3
            ).forEach {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { }
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.outline.copy(0.1f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.icon_profile_circle_bold),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.outline.copy(0.8f)
                    )
                    Text(
                        text = "User Details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline.copy(0.8f)
                    )
                }
            }
        }
    }
}