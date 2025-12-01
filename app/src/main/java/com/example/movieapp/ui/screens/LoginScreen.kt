package com.example.movieapp.ui.screens
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Preview(showBackground = true)
@Composable
fun LoginScreen( modifier: Modifier = Modifier){

    // 💡 RECOMMENDATION: Fix the Image modifier chaining here.
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp), // Added horizontal padding for margins
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.movieta),
            contentDescription = "logo",
            modifier = Modifier.size(100.dp) // Removed redundant .fillMaxWidth()
        )
        
        Spacer(Modifier.height(20.dp))
        Text(text = "Login With your account !",fontSize = 30.sp)

        Spacer(Modifier.height(50.dp))

        var nameInput by remember { mutableStateOf("") }
        TextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Username or Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(32.dp))

        var passowrd by remember { mutableStateOf("") }
        TextField(
            value = passowrd,
            onValueChange = { passowrd = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))
        Button(onClick = {}) { }
    }
}