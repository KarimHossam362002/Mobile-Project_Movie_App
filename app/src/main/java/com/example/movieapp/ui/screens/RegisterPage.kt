package com.example.movieapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility
import com.example.movieapp.R
import com.example.movieapp.ui.theme.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Preview(showBackground = true)
@Composable
fun RegisterPage( modifier: Modifier = Modifier,onNavigateToLogin: () -> Unit = {}){
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
            alpha = 0.05f,
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 60.dp, bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            Spacer(Modifier.height(50.dp))

            //creat the acc
            Spacer(Modifier.height(20.dp))
            Text(text = "Create an account",fontSize = 30.sp, color = White)
            Spacer(Modifier.height(10.dp))
            Text(text = "Please fill the form to continue",fontSize = 15.sp, color = White)

            //username field
            Spacer(Modifier.height(50.dp))
            var nameInput by remember { mutableStateOf("") }
            TextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("Full Name") },
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

            //email field
            Spacer(Modifier.height(25.dp))
            var emailinput by remember { mutableStateOf("") }
            TextField(
                value = emailinput,
                onValueChange = { emailinput = it },
                label = { Text("Email") },
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
            Spacer(Modifier.height(25.dp))
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

            //password confirmation
            Spacer(Modifier.height(25.dp))
            var confirmpassword by remember { mutableStateOf("") }
            TextField(
                value = confirmpassword,
                onValueChange = { confirmpassword = it },
                label = { Text("Confirm Password") },
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
            //sign up button
            Spacer(Modifier.height(150.dp))
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = White,
            ),modifier = Modifier.width(300.dp),) {Text("Sign Up")  }

            //sign in text
            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(horizontal = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ){Text(modifier=modifier
                .padding(start = 30.dp),
                text = "have an account ?",
                fontSize = 15.sp, color = White,)
                TextButton(onClick = onNavigateToLogin,
                    contentPadding = PaddingValues(0.dp)) {
                    Text(text = "Sign In",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,)
                }
            }
        }
    }
}