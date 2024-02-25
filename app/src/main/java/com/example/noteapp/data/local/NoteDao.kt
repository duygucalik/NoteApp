package com.example.noteapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.data.local.entity.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface  NoteDao{
    @Query("SELECT * FROM notes ORDER BY createdDate")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id =:id")
    fun getNoteById(id: Long): Flow<Notes>

    @Query ( "SELECT * FROM notes WHERE isDone = 1 ORDER BY  createdDate DESC " )
    fun getDoneNotes():Flow<List<Notes>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteNote(id: Long)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Notes)



}