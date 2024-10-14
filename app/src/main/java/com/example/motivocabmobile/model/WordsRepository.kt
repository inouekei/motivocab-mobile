package com.example.motivocabmobile.model

import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getAllWordStream(): Flow<List<Word>>
    fun getWordStream(id: Int): Flow<Word?>
    suspend fun insertWord(word: Word)
    suspend fun deleteWord(word: Word)
    suspend fun updateWord(word: Word)
    fun isWordExists(id: Int): Flow<Boolean>
}