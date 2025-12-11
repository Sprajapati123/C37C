package com.example.c37c.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.example.c37c.R
import com.example.c37c.model.UserModel
import com.example.c37c.repository.UserRepo
import com.example.c37c.repository.UserRepoImpl
import com.example.c37c.ui.theme.Blue
import com.example.c37c.ui.theme.PurpleGrey80
import com.example.c37c.ui.theme.White
import com.example.c37c.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterBody()
        }
    }
}

@Composable
fun RegisterBody() {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val userViewModel = remember { UserViewModel(UserRepoImpl()) }


    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    val datepicker = DatePickerDialog(
        context, { _, y, m, d ->
            selectedDate = "$y/${m + 1}/$d"

        }, year, month, day
    )

    val activity = context as? Activity

    val sharedPreference = context.getSharedPreferences(
        "User",
        Context.MODE_PRIVATE
    )

    val editor = sharedPreference.edit()

    val snackbarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()



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
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                "Sign Up",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Blue,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )




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
                value = selectedDate,
                onValueChange = {
                    selectedDate = it
                },
                enabled = false,
                placeholder = {
                    Text("dd/mm/yyyy")
                },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = PurpleGrey80,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = PurpleGrey80,
                    focusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datepicker.show()
                    }
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(15.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
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


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = terms,
                    onCheckedChange = {
                        terms = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Blue,
                        checkmarkColor = White
                    )
                )
                Text("I agree to terms & Conditions")
            }

            Button(
                onClick = {
                    if(!terms){
                        Toast.makeText(context,
                        "Please agree to terms & conditions",
                        Toast.LENGTH_LONG
                        ).show()
                    }else{
                        userViewModel.register(email,password){
                           success,message,userId->
                            if(success){
                                val model = UserModel(
                                    userId = userId,
                                    email = email,
                                    firstName = "",
                                    lastName = "",
                                    dob = selectedDate,
                                    contact = ""
                                )
                                userViewModel.addUserToDatabase(userId,model){
                                    success,message->
                                    if(success){
                                        Toast.makeText(context,
                                            message,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }else{
                                        Toast.makeText(context,
                                            message,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }else{
                                Toast.makeText(context,
                                    message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
//                if(!terms){
//                    Toast.makeText(context,
//                        "Please agree to terms & conditions",
//                        Toast.LENGTH_LONG
//                        ).show()
//                }else{
//                    editor.putString("email",email)
//                    editor.putString("password",password)
//                    editor.putString("date",selectedDate)
//                    editor.apply()
//                    coroutineScope.launch {
//                        snackbarHostState.showSnackbar("registered")
//                    }
////                    Toast.makeText(context,
////                        "Successfully Registered",
////                        Toast.LENGTH_LONG
////                    ).show()
////
//                    activity.finish()
//                }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text("Sign Up")
            }

            Text(buildAnnotatedString {
                append("Already have account? ")

                withStyle(SpanStyle(color = Blue)) {
                    append("Sign In")
                }
            })
        }
    }
}

@Preview
@Composable
fun PreviewRegister() {
    RegisterBody()
}