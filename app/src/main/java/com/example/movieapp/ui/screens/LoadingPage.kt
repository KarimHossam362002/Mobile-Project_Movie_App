package com.example.movieapp.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.movieapp.ui.theme.Basecolor
import com.example.movieapp.ui.theme.*


//custom ProgressIndicator
@Composable
fun ProgressIndicator(
    size: Dp = 70.dp,
    color: Color = Darkblue,
    strokeWidth: Dp = 3.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "cool_square")
    val outerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outer_rot"
    )
    val innerRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "inner_rot"
    )
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Canvas(modifier = Modifier.size(size)) {
        val center = Offset(size.toPx() / 2, size.toPx() / 2)
        val outerSize = (size * pulse).toPx()
        rotate(outerRotation) {
            drawRect(
                color = color,
                topLeft = Offset(center.x - outerSize / 2, center.y - outerSize / 2),
                size = Size(outerSize, outerSize),
                style = Stroke(width = strokeWidth.toPx())
            )
        }
        val innerSize = (size * 0.55f * pulse).toPx()

        rotate(innerRotation) {
            drawRect(
                color = color.copy(alpha = 0.7f),
                topLeft = Offset(center.x - innerSize / 2, center.y - innerSize / 2),
                size = Size(innerSize, innerSize),
                style = Stroke(width = (strokeWidth * 0.9f).toPx())
            )
        }
        drawCircle(
            color = Darkblue.copy(alpha = 0.1f),
            radius = (size* 0.55f * pulse).toPx(),
            center = center,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Blue),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            ProgressIndicator(color = Darkblue)
            Spacer(modifier = Modifier.height(28.dp))
            Text("Loading...", fontSize = 25.sp,style = TextStyle(
                color = Basecolor,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
            )
            )
        }
    }
}