package com.example.noteapp

import android.annotation.SuppressLint
import android.media.ImageWriter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.theme.NoteAppTheme
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
                    Scaffold (
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = colorResource(id = R.color.purples), //arka plan rengi
                                    titleContentColor = colorResource(id = R.color.black), //yazı rengi
                                ),
                                title = {
                                    Text("NoteApp",
                                        fontSize = 24.sp,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            )
                        },

                        bottomBar = {
                            NavigationBottomBar(navController = navController)

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


