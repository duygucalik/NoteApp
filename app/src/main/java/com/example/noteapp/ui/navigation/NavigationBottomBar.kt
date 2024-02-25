package com.example.noteapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottomBar(
    navController: NavHostController
) {
    val list = listOf(
        Destination.Home,
        Destination.Done
    )
    val selectedIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier =Modifier.clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
        containerColor= colorResource(id = R.color.purples)
    ) {
        list.forEachIndexed { index, destination ->
            NavigationBarItem(
                label = { Text(
                    text = destination.title,
                    color=Color.White,
                    fontSize = 16.sp
                ) },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(list[index].route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        tint=Color.White,
                        imageVector = destination.icon,
                        contentDescription = destination.title
                    )
                })
        }
    }
}
