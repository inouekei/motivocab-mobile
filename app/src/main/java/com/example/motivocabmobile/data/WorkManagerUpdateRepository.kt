package com.example.motivocabmobile.data

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.motivocabmobile.workers.UpdateWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class WorkManagerUpdateRepository(context: Context) : UpdateRepository {
    private val workManager = WorkManager.getInstance(context)
    override val outputWorkInfo: Flow<WorkInfo?> = MutableStateFlow(null)
    override fun updateDatabase() {
        val updateBuilder = PeriodicWorkRequestBuilder<UpdateWorker>(
            1,
            TimeUnit.MINUTES
        )
        workManager.enqueueUniquePeriodicWork(
            "regular_update_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            updateBuilder.build()
        )
    }

    override fun cancelWork() {
        TODO("Not yet implemented")
    }
}