package com.example.noteapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Notes
    (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val content : String,
    val title:String,
    val createdDate : Date,
    val isDone : Boolean =false
)
