package com.niemannsolutions.composecollectwords.ui

import androidx.lifecycle.ViewModel
import com.niemannsolutions.composecollectwords.data.CollectWordsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CollectWordsViewModel : ViewModel() {
    private val _wordsState = MutableStateFlow(CollectWordsState())
    val wordsState: StateFlow<CollectWordsState> = _wordsState.asStateFlow()

    fun addWord(word: String) {
        _wordsState.update { currentState ->
            val wordsUpdated: MutableList<String> = currentState.words.toMutableList()
            wordsUpdated.add(word)
            currentState.copy(words = wordsUpdated)
        }
    }

    fun clearWords() {
        _wordsState.update { currentState ->
            currentState.copy(words = listOf())
        }
    }

    fun removeWord(word: String) {
        _wordsState.update { currentState ->
            val wordsUpdated: MutableList<String> = currentState.words.toMutableList()
            wordsUpdated.remove(word)
            currentState.copy(words = wordsUpdated)
        }
    }
}