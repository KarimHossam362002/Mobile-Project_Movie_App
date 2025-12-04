package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.data.User
import com.example.movieapp.ui.theme.*


/**
 * A composable function that displays the user's profile information.
 *
 * This screen shows the user's profile icon, username, and email address.
 * It also includes a button to edit the profile, although the functionality
 * is not yet implemented. The layout is centered vertically and horizontally
 * with a dark background theme.
 *
 * @param currentUser The [User] object containing the data to be displayed.
 * @param modifier The [Modifier] to be applied to the root Column layout.
 */
@Composable
fun ProfileScreen(
    currentUser: User,  // Pass user data
    modifier: Modifier = Modifier
) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFF1A1A2E))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = currentUser.username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentUser.email ?: "No email",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = DarkPurple)
            ) {
                Text("Edit Profile", color = Color.White)
            }
        }
    }
