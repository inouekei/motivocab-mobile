package com.example.motivocabmobile.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.motivocabmobile.model.Answer
import kotlinx.coroutines.flow.Flow

interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Answer)

    @Update
    suspend fun update(item: Answer)

    @Delete
    suspend fun delete(item: Answer)

    @Query("SELECT * from words WHERE id = :id")
    fun getAnswer(id: Int): Flow<Answer>

    @Query("SELECT * from words ORDER BY back ASC")
    fun getAllAnswers(): Flow<List<Answer>>
}