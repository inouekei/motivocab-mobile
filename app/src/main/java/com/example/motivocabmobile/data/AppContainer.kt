package com.example.motivocabmobile.data

import android.content.Context

interface AppContainer {
    val wordsRepository: WordsRepository
//    val updateRepository: UpdateRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
//    override val updateRepository = WorkManagerUpdateRepository(context)
    override val wordsRepository: WordsRepository by lazy {
        OfflineWordsRepository(WordDatabase.getDatabase(context).wordDao())
    }
}