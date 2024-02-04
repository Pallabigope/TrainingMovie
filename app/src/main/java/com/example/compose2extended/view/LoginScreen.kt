package com.example.compose2extended.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose2extended.R
import com.example.compose2extended.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    // State variables for email, password, and password visibility
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    // Get the software keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModelScope= rememberCoroutineScope()
// UI layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.acorns),
            contentDescription = null, // provide a meaningful description
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Sign-up link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, end = 16.dp),
                //contentAlignment = Alignment.TopEnd
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Sign Up",
                    style = LocalTextStyle.current,
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        navController.navigate("sign_up")
                    },
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier    // Spacer for layout
                .height(120.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center, // Center horizontally
            ) {
                // LoginPage heading
                Text(
                    text = "Login to Shopping Tracker",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
            }
            // Card containing login fields
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, //Card background color
                    contentColor = Color.Black  //Card content color,e.g.text
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White),
                ) {
                    Spacer(modifier = Modifier
                        .height(16.dp))
                    OutlinedTextField(     // Email input field
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp)
                    )
                    Spacer(modifier = Modifier
                        .height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(20.dp),
                            singleLine = true,
                            visualTransformation =
                            if (passwordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passwordVisibility)
                                    R.drawable.baseline_visibility_24
                                else R.drawable.baseline_visibility_off_24

                                val description =
                                    if (passwordVisibility) "Hide password" else "Show password"

                                Icon(
                                    painter = painterResource(id = image),
                                    contentDescription = description,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable {
                                            passwordVisibility = !passwordVisibility
                                        },
                                    tint = Color(0xFF619943)
                                )
                            }

                        )
                    }
                    Spacer(modifier = Modifier
                        .height(20.dp))
                    Button(
                        onClick = {
                            // Handle login
                            viewModelScope.launch {
                                val isValidCredentials=userViewModel.checkCredentials(email,password)
                                if (isValidCredentials){
                                    navController.navigate("ProductListScreen")
                                } else {

                                }
                            }

                            keyboardController?.hide()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(20.dp),
                        enabled = email.isNotBlank() && password.isNotBlank(),
                        //.background(Color.Cyan)
                    ) {
                        Text("Log in")
                    }
                    Spacer(modifier = Modifier
                        .height(16.dp))
                    Text(
                        text = "Forgot password?",
                        style = LocalTextStyle.current,
                        color = Color.Black,
                        modifier = Modifier
                            .clickable {}
                            .align(Alignment.CenterHorizontally),
                        textDecoration = TextDecoration.Underline

                    )
                }
            }
        }
    }
}

