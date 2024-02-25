package com.example.noteapp.ui.done

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.common.Resource
import com.example.noteapp.data.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.data.domain.usecase.DoneNoteUseCase
import com.example.noteapp.data.domain.usecase.UpdateNoteUseCase
import com.example.noteapp.data.local.entity.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneNoteViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getFavNotesUseCase: DoneNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase

) : ViewModel(){

    private val _state: MutableStateFlow<DoneState> = MutableStateFlow(DoneState())
    val state: StateFlow<DoneState> = _state.asStateFlow()

    init {
        getDoneNotes()
    }

    private fun getDoneNotes() {
        getFavNotesUseCase().onEach {
            _state.value = DoneState(notes = Resource.Success(it))
        }

            .catch {
                _state.value = DoneState(Resource.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    fun onDoneChange(note: Notes) {
        viewModelScope.launch {
            updateNoteUseCase(note.copy(isDone = !note.isDone))
        }
    }

    fun onDeleteNote(id: Long) {
        viewModelScope.launch {
            deleteNoteUseCase(id)
        }
    }

}


data class DoneState(
    val notes: Resource<List<Notes>> = Resource.Loading
)