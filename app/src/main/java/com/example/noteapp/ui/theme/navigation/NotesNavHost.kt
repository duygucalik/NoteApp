package com.example.noteapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.noteapp.ui.theme.complate.ComplatedScreen
import com.example.noteapp.ui.theme.data.Notes
import com.example.noteapp.ui.theme.detail.NoteDetail
import com.example.noteapp.ui.theme.home.CreateNoteScreen
import com.example.noteapp.ui.theme.home.HomeScreen
import com.google.gson.Gson

@Composable
fun NotesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            HomeScreen(navController)
        }
        composable("Complated") {
            ComplatedScreen()
        }
        composable("Create Note"){
            CreateNoteScreen()
        }
        composable("Note Detail/{note}" , arguments = listOf(
            navArgument("note"){type=NavType.StringType}
        )) {
            val json = it.arguments?.getString("note")
            val not =Gson().fromJson(json, Notes ::class.java)
            NoteDetail(note = not)
        }
    }
}
