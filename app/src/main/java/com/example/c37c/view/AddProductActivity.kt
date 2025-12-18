package com.example.c37c.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c37c.model.ProductModel
import com.example.c37c.repository.ProductRepoIMpl
import com.example.c37c.ui.theme.Blue
import com.example.c37c.ui.theme.PurpleGrey80
import com.example.c37c.viewmodel.ProductViewModel

class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddProductBody()
        }
    }
}

@Composable
fun AddProductBody() {

    var productViewModel = remember { ProductViewModel(ProductRepoIMpl()) }
    var pname by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    val context = LocalContext.current
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                OutlinedTextField(
                    value = pname,
                    onValueChange = { data ->
                        pname = data
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    placeholder = {
                        Text("Product name")
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
                    value = pPrice,
                    onValueChange = { data ->
                        pPrice = data
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    placeholder = {
                        Text("Product price")
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
                    value = pDesc,
                    onValueChange = { data ->
                        pDesc = data
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    placeholder = {
                        Text("Product desc")
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

                Button(
                    onClick = {
                        var model = ProductModel(
                            "",
                            pname,
                            pPrice.toDouble(),
                            pDesc,
                            ""
                        )
                        productViewModel.addProduct(model) { success, message ->
                            if (success) {
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
//                    if (localEmail == email && localPassword == password) {
//                        val intent = Intent(
//                            context, DashboardActivity::class.java
//                        )
//                        context.startActivity(intent)
//                        activity.finish()
//                    } else {
//                        Toast.makeText(context,
//                            "Invalid login",
//                            Toast.LENGTH_LONG).show()
//                    }

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
                    Text("Add Product")
                }
            }
        }
    }
}
