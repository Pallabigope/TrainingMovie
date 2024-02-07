package com.example.compose2extended.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose2extended.R
import com.example.compose2extended.viewmodel.UserViewModel

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
           //content
        }
    }
}
@Composable
fun SignUpPage(
    navController: NavController,
    maxLength: Int,
    userViewModel: UserViewModel= viewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    // SignUp page content here
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
        Spacer(modifier = Modifier.height(120.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Input fields for SignUp
            Text(
                text = "Create your account",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(90.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = {  if (it.length <= maxLength) {
                    firstName = it }},
                label = { Text("First Name") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { if (it.length <= maxLength) {
                    lastName = it } },
                label = { Text("Last Name") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            OutlinedTextField(
                value = email,
                onValueChange = { if (it.length <= maxLength) {
                   email = it } },
                label = { Text("Email") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                }
            )
            OutlinedTextField(
                value = address,
                onValueChange = { if (it.length <= maxLength) {
                    address = it } },
                label = { Text("Address") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            OutlinedTextField(
                value = dob,
                onValueChange = { if (it.length <= maxLength) {
                   dob = it } },
                label = { Text("DOB") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { if (it.length <= maxLength) {
                   password = it } },
                label = { Text("Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.flat_green),
                    unfocusedBorderColor = Color.Black,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
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
            // SignUp button
            Button(
                onClick = {
                    userViewModel.addUser(firstName, lastName,email, address, dob, password)
                    // Handle SignUp logic
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign Up", color = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center, // Center horizontally
            )
            {  Text(
                    text = "Already have an account? ",
                    fontSize = 16.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Sign In",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.flat_green),
                    modifier = Modifier
                        .clickable {
                   // Handle navigation to SignIn screen here
                            navController.navigate("sign_in")
                        }
                )
            }

        }
    }
}


