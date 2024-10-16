package com.example.motivocabmobile.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.*
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "answers",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Word::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("wordId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Answer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isCorrect: Boolean,
    val createdAt: Instant = Clock.System.now()
)
