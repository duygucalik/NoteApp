package com.example.noteapp.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.domain.usecase.AddUseCase
import com.example.noteapp.data.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.data.domain.usecase.GetNoteByIdNoteUseCase
import com.example.noteapp.data.domain.usecase.UpdateNoteUseCase
import com.example.noteapp.data.local.entity.Notes
import com.google.android.gms.common.api.internal.BackgroundDetector.initialize
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date


class DetailViewModel  @AssistedInject  constructor(
    private val addNoteUseCase: AddUseCase,
    private val getNoteByIdUseCase: GetNoteByIdNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,

    @Assisted private val id: Long
):ViewModel(){
    var state by mutableStateOf(DetailState())
        private set
    val isFormNotBlank: Boolean
        get() = state.title.isNotEmpty() && state.content.isNotEmpty()
    private val note: Notes
        get() = state.run {
            Notes(
                id,
                title,
                content,
                createdDate,
                isDone
            )
        }

    init {
        initialize()
    }

    private fun initialize() {
        val isUpdatingNote = id != -1L
        state = state.copy(isUpdatingNote = isUpdatingNote)
        if (isUpdatingNote) {
            getNoteById()
        }
    }

    private fun getNoteById() {
        viewModelScope.launch {
            getNoteByIdUseCase(id).collectLatest {
                state = state.copy(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    createdDate = it.createdDate,
                    isDone = it.isDone,
                )
            }
        }
    }

    fun onTitleChanged(title: String) {
        state = state.copy(title = title)
    }

    fun onContentChanged(description: String) {
        state = state.copy(content = description)
    }

    fun onDoneChanged(done: Boolean) {
        state = state.copy(isDone = done)
    }

    fun addOrUpdateNote() = viewModelScope.launch {
        addNoteUseCase(note = note)
    }

}
class DetailViewModelFactory(
    private val id: Long,
    private val assistedFactory: DetailAssistedFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id) as T
    }
}

@AssistedFactory
interface DetailAssistedFactory {
    fun create(id: Long): DetailViewModel
}

data class DetailState(
    val id: Long = 0,
    val title: String = "",
    val content : String = "",
    val createdDate: Date = Date(),
    val isDone: Boolean = false,
    val isUpdatingNote: Boolean = false
)