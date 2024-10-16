package com.example.motivocabmobile.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface UpdateRepository {
    val outputWorkInfo: Flow<WorkInfo?>
    fun updateDatabase()
    fun cancelWork()
}