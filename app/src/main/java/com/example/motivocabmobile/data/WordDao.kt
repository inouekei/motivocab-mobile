package com.example.motivocabmobile.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.motivocabmobile.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Word)

    @Update
    suspend fun update(item: Word)

    @Delete
    suspend fun delete(item: Word)

    @Query("SELECT * from words WHERE id = :id")
    fun getWord(id: Int): Flow<Word>

    @Query("SELECT * from words ORDER BY back ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT EXISTS(SELECT 1 FROM words WHERE id = :id)")
    fun isWordExists(id: Int): Flow<Boolean>
}