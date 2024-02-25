package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.noteapp.data.local.entity.Notes
import com.example.noteapp.ui.detail.DetailAssistedFactory
import com.example.noteapp.ui.detail.NoteDetail
import com.example.noteapp.ui.done.DoneNoteScreen
import com.example.noteapp.ui.done.DoneNoteViewModel
import com.example.noteapp.ui.home.HomeScreen
import com.example.noteapp.ui.home.HomeViewModel
import com.google.gson.Gson

@Composable
fun NotesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    DoneNoteViewModel: DoneNoteViewModel,
    assistedFactory: DetailAssistedFactory
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home.title
    ) {
        composable(route = Destination.Home.title) {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onDoneChange = homeViewModel::onDoneNoteChanged,
                onDeleteNote = homeViewModel::onDeleteNote,
                onNoteClick = {
                    navController.navigateToSingleTop(
                        route = "${Destination.Detail.title}?id=$it"
                    )
                },
            )
        }
        composable(route = Destination.Done.title) {
            val state by DoneNoteViewModel.state.collectAsState()
            DoneNoteScreen(
                state = state,
                modifier = modifier,
                onDoneChange = DoneNoteViewModel::onDoneChange,
                onDeleteNote = DoneNoteViewModel::onDeleteNote,
                onNoteClick = {
                    navController.navigateToSingleTop(
                        route = "${Destination.Detail.title}?id = $it"
                    )
                }
            )
        }
        /*composable("Create Note"){
            CreateNoteScreen()
        }*/
        composable(
            route = "${Destination.Detail.title}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1L
            NoteDetail(
                id = id, assistedFactory = assistedFactory,
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}

fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
