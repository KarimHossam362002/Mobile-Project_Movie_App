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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.movieapp.ui.theme.Basecolor
import com.example.movieapp.ui.theme.* // Assuming this imports White and other colors
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ProgressIndicator(
    size: Dp = 70.dp,
    color: Color = White, // Default is already White, using it for clarity
    strokeWidth: Dp = 3.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "cool_square")

    // Outer ring rotation
    val outerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outer_rot"
    )

    // Inner ring counter-rotation
    val innerRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "inner_rot"
    )

    // Middle ring rotation
    val middleRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "middle_rot"
    )

    // Pulse effect
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Glow effect
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Canvas(modifier = Modifier.size(size)) {
        val center = Offset(size.toPx() / 2, size.toPx() / 2)

        // Glow effect background
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    color.copy(alpha = glowAlpha * 0.4f),
                    Color.Transparent
                ),
                center = center,
                radius = size.toPx() * 0.6f
            ),
            radius = size.toPx() * 0.6f,
            center = center
        )

        // Outer rotating arcs
        rotate(outerRotation, center) {
            for (i in 0..2) {
                val startAngle = i * 120f
                drawArc(
                    color = color.copy(alpha = 0.9f),
                    startAngle = startAngle,
                    sweepAngle = 80f,
                    useCenter = false,
                    topLeft = Offset(
                        center.x - (size * pulse * 0.45f).toPx(),
                        center.y - (size * pulse * 0.45f).toPx()
                    ),
                    size = Size(
                        (size * pulse * 0.9f).toPx(),
                        (size * pulse * 0.9f).toPx()
                    ),
                    style = Stroke(
                        width = strokeWidth.toPx() * 1.2f,
                        cap = StrokeCap.Round
                    )
                )
            }
        }

        // Middle rotating ring with gradient
        rotate(middleRotation, center) {
            drawCircle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        color.copy(alpha = 0.8f),
                        color.copy(alpha = 0.3f),
                        color.copy(alpha = 0.8f)
                    ),
                    center = center
                ),
                radius = (size * 0.33f * pulse).toPx(),
                center = center,
                style = Stroke(
                    width = strokeWidth.toPx() * 0.8f,
                    cap = StrokeCap.Round
                )
            )
        }

        // Inner counter-rotating dots
        rotate(innerRotation, center) {
            for (i in 0..3) {
                val angle = (i * 90f) * Math.PI / 180.0
                val radius = (size * 0.2f).toPx()
                val dotX = center.x + (radius * cos(angle)).toFloat()
                val dotY = center.y + (radius * sin(angle)).toFloat()

                drawCircle(
                    color = color.copy(alpha = 0.7f),
                    radius = strokeWidth.toPx() * 1.5f * pulse,
                    center = Offset(dotX, dotY)
                )
            }
        }

        // Center pulsing circle
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    color.copy(alpha = 0.6f),
                    color.copy(alpha = 0.2f)
                ),
                center = center,
                radius = (size * 0.12f * pulse).toPx()
            ),
            radius = (size * 0.12f * pulse).toPx(),
            center = center
        )

        // Inner solid circle
        drawCircle(
            color = color.copy(alpha = 0.9f),
            radius = (size * 0.06f * pulse).toPx(),
            center = center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreen() {
    // Explicitly using Color.White for the indicator as requested
    val indicatorColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Basecolor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Updated call to use White as the indicator color
            ProgressIndicator(color = indicatorColor)

            Spacer(modifier = Modifier.height(28.dp))

            Text("Loading...", fontSize = 25.sp,style = TextStyle(
                color = indicatorColor, // Using White for text for visibility
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
            )
            )
        }
    }
}