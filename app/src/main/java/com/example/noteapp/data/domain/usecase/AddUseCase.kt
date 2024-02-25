package com.example.noteapp.data.domain.usecase

import com.example.noteapp.data.domain.repository.Repository
import com.example.noteapp.data.local.entity.Notes
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note:Notes) = repository.insert(note)
}