package com.example.noteapp.ui.theme.home

import android.annotation.SuppressLint
import android.app.Activity
import android.service.voice.VoiceInteractionSession.ActivityId
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.R
import com.example.noteapp.ui.theme.data.Notes
import com.google.gson.Gson
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen (navController: NavController) {

    val noteList =  remember{
        mutableStateListOf<Notes>()
    }
    LaunchedEffect(key1 = true){
        val n1= Notes(1,"Birinci note" , "Note 1 ")
        val n2= Notes(1,"İkinci note" , "Note 2 ")
        val n3= Notes(1,"Üçüncü note" ,  "Note 3 ")
        val n4= Notes(1,"Dördüncü note" ,  "Note 4 ")
        val n5= Notes(1,"Beşinci note" , "Note 5 ")
        val n6= Notes(1,"Birinci note" , "Note 6")
        val n7= Notes(1,"İkinci note" ,  "Note 7 ")
        val n8= Notes(1,"Üçüncü note" , "Note 8 ")
        val n9= Notes(1,"Dördüncü note" , "Note 9")
        val n10= Notes(1,"Beşinci note" , "Note 10 ")

        noteList.add(n1)
        noteList.add(n2)
        noteList.add(n3)
        noteList.add(n4)
        noteList.add(n5)
        noteList.add(n6)
        noteList.add(n7)
        noteList.add(n8)
        noteList.add(n9)
        noteList.add(n10)
    }

   /* Scaffold (
        content = {


        }

    )*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        LazyColumn {
        items(
            count = noteList.count(),
            itemContent = {
                val note = noteList[it]
                Card (
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(10.dp)
                ){
                    Row(
                        modifier = Modifier.clickable {
                            val noteJson = Gson().toJson(note)
                            navController.navigate("Note Detail/$noteJson")
                        }
                    ){
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxWidth()
                        ) {
                            Column (
                                verticalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxHeight()
                            ) {

                                Text(text = note.title, fontSize = 24.sp)
                                Text(text = note.note, fontSize = 16.sp )
                            }
                            IconButton(
                                onClick = {
                                    val noteJson = Gson().toJson(note)
                                    navController.navigate("Note Detail/$noteJson") }) {
                                Icon(painter = painterResource(id = R.drawable.ic_detail),
                                    contentDescription = "")
                            }
                        }

                    }

                }
            }
        )
    }
       /* Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.notes),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .padding(top = 180.dp)
                .align(Alignment.Center),
            fontSize = 18.sp,
            text = "Create your first note !",
            color = Color.Black
        )*/
        FabButton(navController)
    }
    }

@Composable
fun FabButton(navController: NavController) {
    FloatingActionButton(
        onClick = {navController.navigate("Create Note")},
        modifier= Modifier
            .padding(10.dp)
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomEnd),
        containerColor = colorResource(id = R.color.purples),
        content = {
            Icon(
                Icons.Filled.Edit, "Floating action button.",
                tint = Color.White
            )
        }
    )
}
