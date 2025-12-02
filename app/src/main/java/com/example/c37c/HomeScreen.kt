package com.example.c37c

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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    var data = listOf(
        R.drawable.gmail,
        R.drawable.face,
        R.drawable.cat,
        R.drawable.hamster,
        R.drawable.goldfish,
        R.drawable.dog
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),

        ) {
        item {
            Text("Welcome, Good morning ")
        }

        item {
            LazyRow {
                items(data.size){index->
                    Image(
                        painter = painterResource(data[index]),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp).padding(end = 10.dp)
                    )
                }
            }
        }

        item{
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(end = 10.dp)
            )
        }

        item {
            LazyRow {
                items(data.size){index->
                    Image(
                        painter = painterResource(data[index]),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp).padding(end = 10.dp)
                    )
                }
            }
        }
        items(data.size){index->
            Image(
                painter = painterResource(data[index]),
                contentDescription = null,
                modifier = Modifier.size(70.dp).padding(end = 10.dp)
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            ) {
                items(data.size){index->
                    Image(
                        painter = painterResource(data[index]),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp).padding(end = 10.dp)
                    )
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