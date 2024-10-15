package com.example.motivocabmobile.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.motivocabmobile.MotivocabMobileApplication
import com.example.motivocabmobile.ui.screens.ListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ListViewModel(listApplication().container.wordsRepository)
        }
    }
}

fun CreationExtras.listApplication(): MotivocabMobileApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MotivocabMobileApplication)