package com.example.noteapp.data.domain.usecase

import com.example.noteapp.data.domain.repository.Repository
import javax.inject.Inject

class DeleteAllNoteUseCase @Inject constructor(
    private val repository: Repository
) {

  //  suspend operator fun invoke() = repository.delete()

}