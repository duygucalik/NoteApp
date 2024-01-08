package com.example.noteapp.ui.theme.home

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen () {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(text = "Notes", fontSize = 32.sp, color = MaterialTheme.colorScheme.surface)
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.note),
            contentDescription = "No Notes Image"
        )
        Text(
            modifier = Modifier
                .padding(top = 120.dp)
                .align(Alignment.Center),
            text = "Create your first note !",
            color = Color.White
        )
        FabButton()
    }

    }

@Composable
fun FabButton() {
        FloatingActionButton(
            onClick = {/*navController.navigate("kisi_kayit_sayfa")*/},
            modifier= Modifier.padding(10.dp)
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomEnd),
            containerColor = Color.Black,
            content = {
                Icon(
                    Icons.Filled.Add, "Floating action button.",
                    tint = Color.White
                )
            }
        )
}
