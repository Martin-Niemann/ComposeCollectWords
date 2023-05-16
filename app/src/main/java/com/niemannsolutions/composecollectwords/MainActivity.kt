package com.niemannsolutions.composecollectwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    CollectWordsContent(words = words.toList().toString(), addWord = {words.add(it)}, clearWords = { words.clear() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectWordsContent(words: String, addWord: (String) -> Unit, clearWords: () -> Unit) {
    var word: String by remember {
        mutableStateOf("")
    }

    Column {
        OutlinedTextField(
            value = word,
            onValueChange = {word = it},
            label = { Text(text = "Write a word")},
            keyboardOptions = KeyboardOptions(KeyboardCapitalization.Words)
        )
        Row {
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
        Text(
            text = words,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectWordsPreview() {
    ComposeCollectWordsTheme {
        CollectWordsScreen()
    }
}

