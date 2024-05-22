package com.example.taskmanagement.section.home_section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.taskmanagement.R
import com.example.taskmanagement.viewModel.AuthViewModel
import com.google.android.gms.common.util.Hex

@Composable
fun HelloSection() {
    var search_value by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(size = 32.dp)
                )
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                Modifier
                    .width(176.dp)
                    .height(94.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .width(176.dp)
                        .height(70.dp),
                    text = "Hello,\n" +
                            "${
                                AuthViewModel().auth.currentUser?.displayName?.subSequence(
                                    AuthViewModel().auth.currentUser?.displayName?.lastIndexOf(" ")!!.toInt().plus(1),
                                    AuthViewModel().auth.currentUser?.displayName?.length!!.toInt()
                                )
                            }",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    modifier = Modifier
                        .width(176.dp)
                        .height(16.dp),
                    text = "15 Tasks Pending",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.surfaceDim
                )
            }
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        Color.Blue,
                        RoundedCornerShape(99.dp)
                    )
                    .clip(RoundedCornerShape(99.dp)),
                model = "https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIyLTA1L3B4MTM2NjcxMC1pbWFnZS1rd3Z4eGVxcC5qcGc.jpg",
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color.Black,
                    ambientColor = Color.Black,
                    shape = RoundedCornerShape(99.dp)
                )
                .background(
                    MaterialTheme.colorScheme.inverseOnSurface,
                    RoundedCornerShape(99.dp)
                )
                .width(240.dp)
                .height(32.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp),
                tint = MaterialTheme.colorScheme.outline.copy(0.6f)
            )
            BasicTextField(
                modifier = Modifier
                    .weight(1f),
                value = search_value,
                onValueChange = {search_value = it},
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                decorationBox = {
                    Text(
                        text = if (search_value.isEmpty()) "Search for anything..." else "",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.outline.copy(0.6f)
                    )
                    it()
                },
                singleLine = true
            )
        }
    }
}