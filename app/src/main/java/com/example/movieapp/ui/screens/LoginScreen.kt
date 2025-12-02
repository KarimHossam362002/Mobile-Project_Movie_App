package com.example.movieapp.ui.screens
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.Black
import com.example.movieapp.ui.theme.Blue
import com.example.movieapp.ui.theme.Darkblue
import com.example.movieapp.ui.theme.White

@Preview(showBackground = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0, 9, 17))
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.1f,
        )

        Column(
            modifier = Modifier // Note: Use Modifier here, not the passed 'modifier' to avoid double padding
                .fillMaxSize() // 1. CHANGE: Column must fill size for weight to work
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(100.dp)) // You might want to reduce this on small screens too

            // Icon
            Image(
                painter = painterResource(id = R.drawable.movieicon),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp)
            )

            // Welcome Text
            Spacer(Modifier.height(20.dp))
            Text(text = "Welcome back!", fontSize = 30.sp, color = White)
            Spacer(Modifier.height(10.dp))
            Text(text = "Please sign in to your account", fontSize = 15.sp, color = White)

            // Username field
            Spacer(Modifier.height(50.dp))
            var nameInput by remember { mutableStateOf("") }
            TextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("Username or Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = White,
                    focusedTextColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    unfocusedContainerColor = Darkblue,
                    focusedContainerColor = Darkblue,
                )
            )

            // Password field
            Spacer(Modifier.height(32.dp))
            var password by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = White,
                    focusedTextColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    unfocusedContainerColor = Darkblue,
                    focusedContainerColor = Darkblue,
                )
            )

            // 2. CHANGE: Replace fixed 170.dp with weight(1f)
            // This pushes the button down, but keeps it on screen
            Spacer(Modifier.weight(1f))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = White,
                ),
                modifier = Modifier
                    .fillMaxWidth() // Usually looks better than fixed width
                    .height(50.dp) // Give button some height
            ) {
                Text("Sign in")
            }

            // Sign Up Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp), // Add padding at the very bottom
                horizontalArrangement = Arrangement.Center, // Center the row
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Don't have an account?", fontSize = 15.sp, color = White)
                TextButton(onClick = {}, contentPadding = PaddingValues(start = 5.dp)) {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}