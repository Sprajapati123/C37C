package com.example.c37c.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c37c.R
import com.example.c37c.model.ProductModel
import com.example.c37c.repository.ProductRepoIMpl
import com.example.c37c.ui.theme.Blue
import com.example.c37c.ui.theme.PurpleGrey80
import com.example.c37c.viewmodel.ProductViewModel

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    val productViewModel = remember { ProductViewModel(ProductRepoIMpl()) }


    var pname by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    val loading = productViewModel.loading.observeAsState(initial = false)
    val products = productViewModel.products.observeAsState(initial = null)

    LaunchedEffect(products.value) {
        productViewModel.getAllProduct()


        products.value?.let {
            pname = it.name
            pDesc = it.description
            pPrice = it.price.toString()
        }
    }


    val allProducts = productViewModel.allProducts
                                    .observeAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
        ) {
        item {
            if(showDialog){
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            var model = ProductModel(
                                products.value!!.productId,
                                pname,
                                pPrice.toDouble(),
                                pDesc,
                                ""

                            )
                            productViewModel.updateProduct(model){
                                success,message->
                                if(success){
                                    showDialog = false
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
                        }) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = {
                        Text("Update Product")
                    },
                    text = {
                        Column {
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

                        }
                    }
                )
            }
        }
        if(loading.value){
            item {
                CircularProgressIndicator()
            }
        }else{
            items(allProducts.value!!.size){index->
                var data = allProducts.value!![index]

                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp)
                ){
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(data.name)
                        Text(data.price.toString())
                        Text(data.description)
                        IconButton(onClick = {
                            showDialog = true
                            productViewModel.getProductById(data.productId)
                        }) {
                            Icon(Icons.Default.Edit,contentDescription = null)
                        }
                        IconButton(onClick = {
                            productViewModel.deleteProduct(data.productId){
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
                        }) {
                            Icon(Icons.Default.Delete,contentDescription = null)
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun HomePre() {
    HomeScreen()
}