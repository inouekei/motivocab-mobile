package com.example.motivocabmobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "words")
data class Word (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val front: String,
    val back: String
)