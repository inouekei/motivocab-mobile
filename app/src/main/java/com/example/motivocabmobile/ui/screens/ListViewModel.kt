package com.example.motivocabmobile.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.model.WordsRepository
import com.example.motivocabmobile.network.ListApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.IOException

interface ListUiState {
//    data class Success(val list: String) : ListUiState
    data class Success(val list: List<Word>) : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

//class ListViewModel(private val wordsRepository: WordsRepository) : ViewModel() {
class ListViewModel() : ViewModel() {
    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

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
//                wordList.forEach loop@{
//                    val word = wordsRepository.getWordStream(it.id).firstOrNull()
//                    if (word == null){
//                        wordsRepository.insertWord(it)
//                    }
//                }
                ListUiState.Success(
//                    "Success: ${wordList.size} words retrieved"
                    wordList
                )
            } catch (e: IOException) {
                ListUiState.Error
            }
        }
    }
}