package com.example.noteapp.ui.home

import android.annotation.SuppressLint
import android.window.SplashScreenView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.common.Resource
import com.example.noteapp.data.local.entity.Notes
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen (
    state: HomeState,
    onDoneChange: (note: Notes) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    when(state.notes){
        is Resource.Loading ->{
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            val notes =state.notes.data
            HomeDetail(
                notes = notes,
                modifier =Modifier ,
                onDoneChange = onDoneChange,
                onDeleteNote = onDeleteNote,
                onNoteClick =onNoteClick
            )
        }
        is Resource.Error -> {
            Text(text = state.notes.message ?: "Unknow Error",
                color=MaterialTheme.colorScheme.error)
        }

        else -> {

        }
    }
    }


@Composable
fun HomeDetail(
    notes: List<Notes>,
    modifier: Modifier,
    onDoneChange: (note: Notes) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(text = "Notes", fontSize = 32.sp, color = MaterialTheme.colorScheme.surface)
        LazyColumn(modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 10.dp)) {
            itemsIndexed(notes) { index, item ->
                NoteItem(
                    index = index,
                    note = item,
                    onDoneChange = onDoneChange,
                    onDeleteNote = onDeleteNote,
                    onNoteClick = onNoteClick
                )
            }
        }
        //Fab(navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    index :Int,
    note: Notes,
    onDoneChange: (note: Notes) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val isEvenIndex = index % 2 == 0
    val shape = when{
        isEvenIndex ->{
            RoundedCornerShape(
                topStart = 50f,
                bottomEnd = 50f
            )
        }
        else ->{
            RoundedCornerShape(
                topEnd = 50f,
                bottomStart = 50f
            )
        }
    }
    val icon = if (note.isDone) Icons.Default.Check
    else Icons.Outlined.CheckCircle
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface),
        shape=shape,
        onClick = {
            onNoteClick(note.id)
        }
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.size(6.dp))

            Text(
                text = note.content,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )

        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { onDoneChange(note) }) {
                Icon(imageVector = icon, contentDescription = null)
            }
            IconButton(onClick = { onDeleteNote(note.id) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}



@Composable
fun EmptyNotes() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.notes),
            contentDescription = "No Notes Image"
        )
        Text(
            modifier = Modifier
                .padding(top = 120.dp)
                .align(Alignment.Center),
            text = "Create your first note !",
            color = Color.White
        )
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


@Preview(showSystemUi = true)
@Composable
fun PrevHome(){
    HomeScreen( state = HomeState(), onDoneChange = {}, onDeleteNote = {}, onNoteClick ={} )
}