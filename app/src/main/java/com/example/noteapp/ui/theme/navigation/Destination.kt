package com.example.noteapp.ui.theme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val route: String,
    val title: String,
    val icon: ImageVector

){
    object Home : Destination("home", "Home", Icons.Rounded.Home)
    object Complated : Destination("complated", "Complated", Icons.Rounded.Done)

}