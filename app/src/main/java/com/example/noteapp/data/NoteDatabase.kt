package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.local.converter.DateConverter
import com.example.noteapp.data.local.entity.Notes

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Notes::class],
    version =1,
    exportSchema = false
)
abstract class NoteDatabase  : RoomDatabase(){
    abstract val noteDao : NoteDao
}