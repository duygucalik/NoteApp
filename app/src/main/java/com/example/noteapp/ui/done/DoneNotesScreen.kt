package com.example.noteapp.ui.done

import android.provider.ContactsContract
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.common.Resource
import com.example.noteapp.data.local.entity.Notes
import com.example.noteapp.ui.home.NoteItem


@Composable
fun DoneNoteScreen (
    state: DoneState,
    modifier: Modifier,
    onDoneChange: (note : Notes) -> Unit,
    onDeleteNote: (Long) -> Unit,
    onNoteClick: (Long) -> Unit,
){
    when (state.notes) {
        is Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Success -> {
            val notes = state.notes.data
            LazyColumn( modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 10.dp)) {
                itemsIndexed(notes) { index, item ->
                    NoteItem(
                        index= index,
                        note = item,
                        onDoneChange = onDoneChange,
                        onDeleteNote = onDeleteNote,
                        onNoteClick = onNoteClick
                    )
                }
            }
        }

        is Resource.Error -> {
            Text(
                text = state.notes.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }


}