package com.example.motivocabmobile.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.motivocabmobile.MotivocabMobileApplication
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.network.ListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

private const val TAG = "UpdateWorker"
class UpdateWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params){
    override suspend fun doWork(): Result {
        makeStatusNotification("Updating", applicationContext)
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val resultString = ListApi.retrofitService.getList()
                val wordList = Json.decodeFromString(
                    ListSerializer(Word.serializer()),
                    resultString
                )
                val motivocabMobileApplication = applicationContext as MotivocabMobileApplication
                val wordsRepository = motivocabMobileApplication.container.wordsRepository
                for (word in wordList) {
                    val firstOrNull = wordsRepository.getWordStream(word.id)
                    if (firstOrNull == null) { wordsRepository.insertWord(word)}
                }
                makeStatusNotification("Updated", applicationContext)
                Result.success()
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    "ERROR!",
                    throwable
                )
                Result.failure()
            }
        }
    }
}
