package com.niemannsolutions.composecollectwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.niemannsolutions.composecollectwords.ui.theme.ComposeCollectWordsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCollectWordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollectWordsScreen()
                }
            }
        }
    }
}

@Composable
fun CollectWordsScreen() {
    val words = remember {
        mutableStateListOf<String>()
    }
    CollectWordsContent(words = words.toList(), addWord = {words.add(it)}, clearWords = { words.clear() }, removeWord = {words.remove(it)})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectWordsContent(words: List<String>, addWord: (String) -> Unit, clearWords: () -> Unit, removeWord: (String) -> Unit) {
    var word: String by remember {
        mutableStateOf("")
    }

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = word,
            onValueChange = {word = it},
            label = { Text(text = "Write a word")},
            keyboardOptions = KeyboardOptions(KeyboardCapitalization.Words)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            horizontalArrangement  =  Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                addWord(word)
                word = ""
            }) {
                Text(text = "Add word")
            }
            Button(onClick = clearWords) {
                Text(text = "Clear words")
            }
        }
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(words) { word ->
                WordItem(
                    word = word,
                    removeWord = removeWord
                )
            }
        }
    }
}

@Composable
fun WordItem(word: String, removeWord: ((String) -> Unit)?) {
    Row(
        horizontalArrangement  =  Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp).padding(start = 8.dp).fillMaxWidth()
    ) {
        Text(
            text = word,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(end = 130.dp)
        )
        IconButton(
            onClick = { removeWord!!(word) }
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete word")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordItemPreview() {
    WordItem(word = "cat", removeWord = null)
}

@Preview(showBackground = true)
@Composable
fun CollectWordsPreview() {
    ComposeCollectWordsTheme {
        CollectWordsScreen()
    }
}

