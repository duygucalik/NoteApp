package com.example.noteapp.data.domain.usecase

import com.example.noteapp.data.domain.repository.Repository
import com.example.noteapp.data.local.entity.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteByIdNoteUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(id: Long): Flow<Notes> {
        return repository.getNoteById(id)
    }

}