package com.example.noteapp.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen() {
    val navController = rememberNavController()

    Scaffold (
        floatingActionButton = {
            Fab(onClick = { navController.navigate("createnote")})
        }
    )
    {

    }
}
