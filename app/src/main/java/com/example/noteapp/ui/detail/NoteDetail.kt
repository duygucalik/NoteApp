package com.example.noteapp.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.data.local.entity.Notes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetail(
    modifier: Modifier = Modifier,
    id: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit
) {

    val viewModel = viewModel(
        modelClass = DetailViewModel::class.java,
        factory = DetailViewModelFactory(
            id = id,
            assistedFactory = assistedFactory
        )
    )

    val state = viewModel.state
    NoteDetail(
        modifier = modifier,
        isUpdatingNote = state.isUpdatingNote,
        title = state.title,
        content = state.content,
        isDone = state.isDone,
        isFormNotBlank = viewModel.isFormNotBlank,
        onDoneChanged = viewModel::onDoneChanged,
        onTitleChanged = viewModel::onTitleChanged,
        onDescChanged = viewModel::onContentChanged,
        onButtonClick = {
            viewModel.addOrUpdateNote()
            navigateUp
        },
        onNavigate = navigateUp
    )

}
@Composable
private fun NoteDetail(
    modifier: Modifier,
    isUpdatingNote: Boolean,
    title: String,
    content: String,
    isDone: Boolean,
    isFormNotBlank: Boolean,
    onDoneChanged: (Boolean) -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TopSection(
            title = title,
            isDone = isDone,
            onDoneChanged = onDoneChanged,
            onTitleChanged = onTitleChanged,
            onNavigate = onNavigate
        )
        Spacer(modifier = Modifier.size(12.dp))
        AnimatedVisibility(isFormNotBlank) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onButtonClick,
                    ) {
                    val icon = if (isUpdatingNote) Icons.Default.Refresh else Icons.Default.Add
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        NotesTextField(
            modifier = Modifier.weight(1f),
            value = content,
            onValueChanged = onDescChanged,
            label = "Description",
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    title: String,
    isDone: Boolean,
    onDoneChanged: (Boolean) -> Unit,
    onTitleChanged: (String) -> Unit,
    onNavigate: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NotesTextField(
            modifier = Modifier.weight(1f),
            value = title,
            onValueChanged = onTitleChanged,
            label = "Title",
            labelAlignment = TextAlign.Center
        )
        IconButton(onClick = { onDoneChanged(!isDone) }) {
            val icon = if (isDone) Icons.Outlined.Check else Icons.Default.CheckCircle
            Icon(imageVector = icon, contentDescription = "doneButton")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesTextField(
    modifier: Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    labelAlignment: TextAlign? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
           containerColor = MaterialTheme.colorScheme.onPrimary,
            textColor = MaterialTheme.colorScheme.onSurface,
        ),
        placeholder = {
            Text(
                text = "Insert $label",
                textAlign = labelAlignment,
                modifier = modifier.fillMaxWidth()
            )
        }
    )
}