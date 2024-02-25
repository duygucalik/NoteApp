package com.example.noteapp.data.local.repository

import com.example.noteapp.data.domain.repository.Repository
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.local.entity.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao :NoteDao
) :Repository {
    override fun getAllNotes(): Flow<List<Notes>> {
        return noteDao.getAllNotes()
    }

    override fun getNoteById(id: Long): Flow<Notes> {
        return noteDao.getNoteById(id)
    }

    override suspend fun insert(note: Notes) {
        noteDao.insertNote(note)
    }

    override suspend fun delete(id: Long) {
        noteDao.deleteNote(id)
    }

    override suspend fun update(note: Notes) {
        noteDao.updateNote(note)
    }

    override fun getDoneNotes(): Flow<List<Notes>> {
        return noteDao.getDoneNotes()
    }
}