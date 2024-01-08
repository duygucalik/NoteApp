package com.example.noteapp.ui.theme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottomBar(
    navController: NavHostController
) {
    val items = listOf(
        Destination.Home,
        Destination.Complated
       /* BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Notes",
            selectedIcon = Icons.Filled.Menu,
            unselectIcon = Icons.Outlined.Menu,
            hasNews = false,
            badgeCount = 4
        )*/
    )
    var selectedItemIndex =  rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.Black
    ) {
        items.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = index == selectedItemIndex.value, // seçilen bottom u animasyon olarak gösterir
                onClick = {
                    selectedItemIndex.value = index
                    navController.navigate(items[index].route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription =destination.title )

                   /* BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }

                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectIcon,
                            contentDescription = null
                        )
                    }*/
                }
            )
        }
    }
}
