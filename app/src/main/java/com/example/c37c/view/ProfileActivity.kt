package com.example.c37c.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c37c.R
import com.example.c37c.ui.theme.CreamWhite
import com.example.c37c.ui.theme.Purple80

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileBody()
        }
    }
}

@Composable
fun ProfileBody() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null
                )
                Text("sandis2025")
                Icon(
                    painter = painterResource(R.drawable.baseline_more_horiz_24),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(R.drawable.apple),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("778")
                    Text("Posts")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("1M")
                    Text("Followers")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("99k")
                    Text("Followings")
                }

            }

            Column(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp)
            ) {
                Text("username")
                Text("username")
                Text("username")
                Text("username")
                Text("username")
            }
            Row {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.apple),
                        contentDescription = null,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clip(
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Text("Story 1")
                }
            }

            OutlinedButton(onClick = {}) {
                Text("Email")

            }
            Button(
                onClick = {
                    //action to trigger
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = CreamWhite
                ),
                border = BorderStroke(2.dp, Purple80)
            ) {
                Text("Follow")
            }

           Row {
               Card(
                   modifier = Modifier
                       .height(200.dp)
                       .weight(1f)
               ) {
                   Column(modifier = Modifier.fillMaxSize())
                   {

                   }
               }
               Spacer(
                   modifier = Modifier.width(20.dp)
               )
               Card(
                   modifier = Modifier
                       .height(200.dp)
                       .weight(1f),
                   colors = CardDefaults.cardColors(
                       containerColor = Color.White
                   ),
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 2.dp
                   ),
                   shape = RoundedCornerShape(15.dp)
               ) {
                   Column(modifier = Modifier.fillMaxSize())
                   {

                   }
               }
           }

        }


    }
}

@Preview
@Composable
fun PreviewProfile() {
    ProfileBody()
}

