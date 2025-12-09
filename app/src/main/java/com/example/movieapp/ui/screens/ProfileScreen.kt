package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.theme.*

@Preview(showBackground = true)
@Composable
fun ProfileContent(modifier: Modifier = Modifier,onNavigateToLogin: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Basecolor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(
                text = "My Profile",
                fontSize = 28.sp,
                color = Color.White
            )
        }
        Spacer(Modifier.height(40.dp))
        Row {
            Box(
            modifier = Modifier
                .padding(30.dp)
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Darkblue,
                            Color(0xFF6366F1)
                        )
                    )
                ) ,contentAlignment = Alignment.Center
            )
        { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Picture",
                tint = Color.White,
                modifier = Modifier.size(70.dp))
        }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Column {
                    Spacer(Modifier.height(15.dp))
                    Text(
                    text = "My Name",
                    fontSize = 28.sp,
                    color = Color.White
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "My Email",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(Modifier.height(200.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
            contentAlignment = Alignment.Center)
        {
            Column {

                Button(
                    onClick = onNavigateToLogin,
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(56.dp).
                        width(400.dp)
                ) {
                    Text(
                        text = "Edit Profile",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(30.dp))
                Button(
                onClick = onNavigateToLogin,
                colors = ButtonDefaults.buttonColors(containerColor = red),
                shape = RoundedCornerShape(10.dp), // Rounded corners for the button
                modifier = Modifier
                    .height(56.dp).
                    width(400.dp)
                )
                {
                    Text(
                        text = "Logout",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }

        }



    }


}
