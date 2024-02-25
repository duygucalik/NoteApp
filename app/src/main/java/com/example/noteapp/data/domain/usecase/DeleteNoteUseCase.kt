package com.example.noteapp.data.domain.usecase

import com.example.noteapp.data.domain.repository.Repository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository : Repository
) {
suspend operator fun invoke(id : Long)= repository.delete(id)
}