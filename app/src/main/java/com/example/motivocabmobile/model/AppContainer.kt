package com.example.motivocabmobile.model

import android.content.Context

interface AppContainer {
    val wordsRepository: WordsRepository
}

class AppDataContainer(private val context: Context) : AppContainer{
    override val wordsRepository: WordsRepository by lazy {
        OfflineWordsRepository(WordDatabase.getDatabase(context).wordDao())
    }
}