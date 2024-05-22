package com.example.taskmanagement.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.taskmanagement.model.Tag

@Composable
fun SemiCircularProgressIndicator(
    progressDone:Float = 0f,
    progressExpired:Float = 0f,
    progressInProgress:Float = 0f,
    ) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(150.dp),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            drawArc(
                color = Color.LightGray,
                -180f,
                180f,
                useCenter = false,
                size = Size(size.width, size.height * 2),
                style = Stroke(12.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        val list = listOf(
            Tag(Color.Blue,
                0.1),
            Tag(Color.Red,
                0.2),
            Tag(Color.Green,
                0.3),
        )
        list.forEach {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(list.reversed().indexOf(it).toFloat().plus(1f))
            ) {
                drawArc(
                    color = it.color,
                    startAngle = (-180f).times((1).plus((list.subList(0,list.indexOf(it)).sumOf { it.value }).times(-1)))
                        .toFloat(),
                    sweepAngle = (180f).times(it.value.toFloat()),
                    useCenter = false,
                    size = Size(size.width, size.height * 2),
                    style = Stroke(12.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Text(
                text = "${progressDone.times(100)}%",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline.copy(0.8f)
                )
        }
    }
}