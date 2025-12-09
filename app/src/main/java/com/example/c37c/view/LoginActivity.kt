package com.example.c37c.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.c37c.R
import com.example.c37c.ui.theme.Black
import com.example.c37c.ui.theme.Blue
import com.example.c37c.ui.theme.PurpleGrey80
import com.example.c37c.ui.theme.White

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginBody()
        }
    }
}

@Composable
fun LoginBody() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val activity = context as Activity

    val snackbarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()

    var showDialog by remember { mutableStateOf(false) }


    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    val localEmail: String? = sharedPreferences.getString("email", "")
    val localPassword: String? = sharedPreferences.getString("password", "")

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
        ) {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        Text("Ok")
                    },
                    dismissButton = {
                        Text("Cancel")
                    },
                    title = {
                        Text("Confirm")
                    },
                    text = {
                        Text("Are you sure you want  to delete")
                    },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                "Sign In",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Blue,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "This is lorem ipsum, this is ecommerce here you can buy any produycts you want",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Black.copy(0.5f)
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                SocialMediaCard(
                    Modifier
                        .height(60.dp)
                        .weight(1f),
                    R.drawable.face,
                    "Facebook"
                )
                Spacer(modifier = Modifier.width(20.dp))

                SocialMediaCard(
                    Modifier
                        .height(60.dp)
                        .weight(1f),
                    R.drawable.gmail,
                    "Gmail"
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )

                Text("OR", modifier = Modifier.padding(horizontal = 20.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { data ->
                    email = data
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                placeholder = {
                    Text("abc@gmail.com")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = PurpleGrey80,
                    focusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(15.dp)
            )


            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { data ->
                    password = data
                },
                placeholder = {
                    Text("********")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        visibility = !visibility
                    }) {
                        Icon(
                            painter = if (visibility)
                                painterResource(R.drawable.baseline_visibility_off_24)
                            else
                                painterResource(R.drawable.baseline_visibility_24),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (!visibility) PasswordVisualTransformation() else VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = PurpleGrey80,
                    focusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(15.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Forget Password", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), style = TextStyle(textAlign = TextAlign.End)
            )

            Button(
                onClick = {


                    if (localEmail == email && localPassword == password) {
                        val intent = Intent(
                            context, DashboardActivity::class.java
                        )
                        context.startActivity(intent)
                        activity.finish()
                    } else {
                        Toast.makeText(context,
                            "Invalid login",
                            Toast.LENGTH_LONG).show()
                    }

//
//                    intent.putExtra("email",email)
//                    intent.putExtra("password",password)
//


//                    showDialog = true
//                    if(email == "ram" && password == "password"){
//                        Toast.makeText(context,
//                            "Login successfully",
//                            Toast.LENGTH_LONG).show()
//                    }else{
//                        coroutineScope.launch {
//                            snackbarHostState.showSnackbar(
//                                "Invalid email or password",
//                                withDismissAction = true
//                            )
//                        }
//                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text("Log In")
            }

            Text(buildAnnotatedString {
                append("Don't have account? ")

                withStyle(SpanStyle(color = Blue)) {
                    append("Sign up")
                }
            }, modifier = Modifier.clickable {
                val intent = Intent(
                    context,
                    RegistrationActivity::class.java
                )

                context.startActivity(intent)


            })
        }
    }
}

@Composable
fun SocialMediaCard(modifier: Modifier, image: Int, label: String) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(label)
        }
    }
}


@Preview
@Composable
fun LoginPreview() {
    LoginBody()
}