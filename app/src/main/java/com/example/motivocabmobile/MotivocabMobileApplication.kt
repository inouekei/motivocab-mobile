package com.example.motivocabmobile

import android.app.Application
import com.example.motivocabmobile.model.AppContainer
import com.example.motivocabmobile.model.AppDataContainer

class MotivocabMobileApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}