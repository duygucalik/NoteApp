package com.example.noteapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapp.ui.theme.Notes.ComplatedScreen
import com.example.noteapp.ui.theme.home.HomeScreen

@Composable
fun NotesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("complated") {
            ComplatedScreen()
        }
    }

}
