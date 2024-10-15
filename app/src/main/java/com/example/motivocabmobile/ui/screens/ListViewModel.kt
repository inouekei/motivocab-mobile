package com.example.motivocabmobile.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.model.WordsRepository
import com.example.motivocabmobile.network.ListApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.IOException

interface ListUiState {
//    data class Success(val list: List<Word>) : ListUiState
    object Success : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

data class LatestListState(val list: List<Word> = listOf())

class ListViewModel(private val wordsRepository: WordsRepository) : ViewModel() {
    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set
    val latestListState: StateFlow<LatestListState> =
        wordsRepository.getAllWordStream().map {LatestListState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = LatestListState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_00L
    }

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch {
            listUiState = try {
                val resultString = ListApi.retrofitService.getList()
                val wordList = Json.decodeFromString(
                    ListSerializer(Word.serializer()),
                    resultString
                )
                wordList.forEach {
                    val word = wordsRepository.getWordStream(it.id).firstOrNull()
                    if (word == null){
                        wordsRepository.insertWord(it)
                    }
                }
                ListUiState.Success
            } catch (e: IOException) {
                ListUiState.Error
            }
        }
    }
}