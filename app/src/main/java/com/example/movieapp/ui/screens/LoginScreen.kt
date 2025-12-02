package com.example.movieapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.*
@Composable
fun LoginScreen( modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Basecolor)
    )
    {
        //background image
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.1f,
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        )
        {
            Spacer(Modifier.height(100.dp))

            //icon
            Image(
                painter = painterResource(id = R.drawable.movieicon),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp)
            )

            //welcome
            Spacer(Modifier.height(20.dp))
            Text(text = "Welcome back!",fontSize = 30.sp, color = White)
            Spacer(Modifier.height(10.dp))
            Text(text = "Please sign in to youre account",fontSize = 15.sp, color = White)

            //username field
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
            //password field
            Spacer(Modifier.height(32.dp))
            var passowrd by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            TextField(
                value = passowrd,
                onValueChange = { passowrd = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.clickable() { passwordVisible = !passwordVisible }
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
            Spacer(Modifier.height(170.dp))
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = White,
            ),modifier = Modifier.width(300.dp),) {Text("Sign in")  }

            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ){Text(text = "Don't have an account ?",fontSize = 15.sp, color = White)
              TextButton(onClick = { },contentPadding = PaddingValues(0.dp)) {
                  Text(text = "Sign Up", color = Color.White,fontSize = 15.sp, fontWeight = FontWeight.Bold,)  }
            }

        }
    }
}