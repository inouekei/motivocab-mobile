package com.example.motivocabmobile

import android.app.Application
import com.example.motivocabmobile.data.AppContainer
import com.example.motivocabmobile.data.AppDataContainer

class MotivocabMobileApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
//        container.updateRepository.updateDatabase()
    }
}