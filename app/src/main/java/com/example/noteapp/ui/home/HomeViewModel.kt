package com.example.noteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.common.Resource
import com.example.noteapp.data.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.data.domain.usecase.GetNotesUseCase
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
class HomeViewModel  @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
):ViewModel(){

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesUseCase().onEach {
            _state.value = HomeState(notes = Resource.Success(it))
        }
            .catch {
                _state.value = HomeState(notes = Resource.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    fun onDoneNoteChanged(note: Notes) {
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

data class HomeState(
    val notes: Resource<List<Notes>> = Resource.Loading
)