package com.example.motivocabmobile.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motivocabmobile.network.ListApi
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    var listUiState: String by mutableStateOf("")
        private set

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch {
            var listResult = ListApi.retrofitService.getList()
            listUiState = listResult
        }
    }
}