package com.niemannsolutions.composecollectwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niemannsolutions.composecollectwords.R
import com.niemannsolutions.composecollectwords.ui.components.WordItem
import com.niemannsolutions.composecollectwords.ui.theme.ComposeCollectWordsTheme

@Composable
fun CollectWordsAddScreen(
    words: List<String>,
    addWord: (String) -> Unit,
    clearWords: () -> Unit,
    removeWord: (String) -> Unit,
    onNextButtonClicked: () -> Unit
) {
    var word: String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = word,
            onValueChange = { word = it },
            label = { Text(text = "Write a word") },
            keyboardOptions = KeyboardOptions(KeyboardCapitalization.Words)
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(words) { word ->
                WordItem(
                    word = word,
                    removeWord = removeWord
                )
            }
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        addWord(word)
                        word = ""
                    }
                ) {
                    Text(text = "Add word")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = clearWords
                ) {
                    Text(text = "Clear words")
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onNextButtonClicked
            ) {
                Text(text = "Next screen")
            }
        }
    }
}

@Preview
@Composable
fun CollectWordsAddScreenPreview() {
    ComposeCollectWordsTheme {
        CollectWordsAddScreen(words = listOf(), addWord = {}, clearWords = {}, removeWord = {}, onNextButtonClicked = {})
    }
}