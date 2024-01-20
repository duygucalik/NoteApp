package com.example.noteapp

import android.annotation.SuppressLint
import android.media.ImageWriter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.theme.home.CreateNoteScreen
import com.example.noteapp.ui.theme.navigation.NavigationBottomBar
import com.example.noteapp.ui.theme.navigation.NotesNavHost


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {

                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentScreen = backStackEntry?.destination?.route

                    Scaffold (
                        topBar = {
                            if (currentScreen != null) {
                                NoteAppTopBar(
                                    currentScreen = currentScreen,
                                    canNavigateBack = navController.previousBackStackEntry != null,
                                    navController
                                )
                            }
                        },

                        bottomBar = {
                            NavigationBottomBar(
                                navController = navController)

                        }
                    )
                    {
                        NotesNavHost(
                            modifier= Modifier
                                .fillMaxSize()
                                .padding(it),
                            navController = navController
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppTopBar(currentScreen: String, canNavigateBack: Boolean, navController : NavController) {
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.purples), //arka plan rengi
            titleContentColor = colorResource(id = R.color.black), //yazı rengi
        ),
        title = {
            Column {
                Text(
                    "NoteApp",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentScreen,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton({
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        },
        actions = {
            if (currentScreen == "Create Note") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Save Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_save), contentDescription = "save button")
                }
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Delete Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete button")
                }
            }
            if (currentScreen == "Complated") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Delete Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete button")
                }
            }
            if (currentScreen == "Note Detail/{note}") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Edit Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit button", tint = Color.Black )
                }
            }

        }
    )
}


