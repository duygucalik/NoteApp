package com.example.noteapp.data.domain.repository

import com.example.noteapp.data.local.entity.Notes
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllNotes() : Flow<List<Notes>>
    fun getNoteById(id :Long) : Flow<Notes>
    suspend fun insert(note : Notes)
    suspend fun delete(id: Long)
    suspend fun update(note : Notes)
    fun getDoneNotes(): Flow<List<Notes>>
}