package com.example.noteapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val route: String,
    val title: String,
    val icon: ImageVector

){
    object Home : Destination("home", "Home", Icons.Filled.Home)
    object Done : Destination("done", "Done", Icons.Filled.Done)
    object Detail : Destination("Detail", "Detail", Icons.Default.Info)

}