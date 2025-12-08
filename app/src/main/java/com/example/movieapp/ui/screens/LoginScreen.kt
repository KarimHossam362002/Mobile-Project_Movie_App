package com.example.movieapp.ui.screens
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.R
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.ui.theme.*
import com.example.movieapp.ui.viewmodel.*
import com.example.movieapp.utils.Hashing.hashPassword


/**
 * A composable function that displays the login screen for the application.
 *
 * This screen includes a background image, an app icon, welcome text,
 * input fields for username/email and password, a "Sign in" button,
 * and a link to navigate to the registration screen.
 *
 * @param modifier A [Modifier] for this composable. Defaults to [Modifier].
 * @param onNavigateToRegister A lambda function to be invoked when the "Sign Up" text is clicked,
 *                             triggering navigation to the registration screen.
 * @param onNavigateToHome A lambda function to be invoked upon successful login,
 *                         triggering navigation to the home screen. Note: The current implementation
 *                         of the "Sign in" button's `onClick` is empty and needs to be updated
 *                         to call this function.
 */

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
//    onNavigationToLoading: () -> Unit
){
    val context = LocalContext.current
    val db = MovieDatabase.getDatabase(context)
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(db.userDao())
    )


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

            TextField(

                value = username,
                onValueChange = { username = it },
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
            var passwordVisible by remember { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange = { password = it },
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
            Spacer(Modifier.height(150.dp))
            Button(
                onClick = {
                    val trimmedUsername = username.trim()
                    val trimmedPassword = password.trim()
                    val hashedPassword = hashPassword(trimmedPassword)
                    viewModel.login(
                        username = trimmedUsername,
                        password = hashedPassword,
                        onSuccess = { user ->
                            Toast.makeText(context, "Welcome ${user.username}", Toast.LENGTH_SHORT).show()
                            onNavigateToHome() // navigate to home
                        },
                        onError = { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = White,
                ),
                modifier = Modifier.width(300.dp),
            ) {Text("Sign in")  }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = "Don't have an account ?",fontSize = 15.sp, color = White)
                TextButton(onClick = onNavigateToRegister ,contentPadding = PaddingValues(0.dp)) {
                    Text(text = "Sign Up", color = Color.White,fontSize = 15.sp, fontWeight = FontWeight.Bold,)  }
            }

        }
    }
}