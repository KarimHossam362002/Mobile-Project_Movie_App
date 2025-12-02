package com.example.movieapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.*
import androidx.compose.foundation.shape.GenericShape

//custom shape for the wavy look
val InwardWaveShape = GenericShape { size, _ ->
    val width = size.width
    moveTo(0f, 0f)
    quadraticBezierTo(
        width * 0.25f, 120f,
        width * 0.5f, 60f
    )
    quadraticBezierTo(
        width * 0.75f, 0f,
        width, 100f
    )
    lineTo(width, size.height)
    lineTo(0f, size.height)
    close()
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreen( modifier: Modifier = Modifier,onNavigateToLogin: () -> Unit = {}){
    Box(
        modifier = modifier.fillMaxSize(),

    )
    {
        //background image
        Image(
            painter = painterResource(id = R.drawable.godfather),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 1f,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(shape = InwardWaveShape)
                .background(Basecolor)
                .padding(horizontal = 24.dp)
                .padding(top = 60.dp, bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

        ) {
            Text(
                text = "Absolute Cinema",
                color = White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "welcome to the show\nget ready to dive into the world of Hollywood ",
                fontSize = 15.sp,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(48.dp))

            Button(
                onClick = onNavigateToLogin,
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(10.dp), // Rounded corners for the button
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp).
                    width(300.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

