package com.example.motivocabmobile.data

import com.example.motivocabmobile.model.Word
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getAllWordStream(): Flow<List<Word>>
    fun getWordStream(id: Int): Flow<Word?>
    suspend fun insertWord(word: Word)
    suspend fun deleteWord(word: Word)
    suspend fun updateWord(word: Word)
    fun isWordExists(id: Int): Flow<Boolean>
}