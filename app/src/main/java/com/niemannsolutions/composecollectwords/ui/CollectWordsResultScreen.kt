package com.niemannsolutions.composecollectwords.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niemannsolutions.composecollectwords.R
import com.niemannsolutions.composecollectwords.ui.components.WordItem
import com.niemannsolutions.composecollectwords.ui.theme.ComposeCollectWordsTheme

@Composable
fun CollectWordsResultScreen(words: List<String>, removeWord: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
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

@Preview
@Composable
fun CollectWordsResultScreenPreview() {
    ComposeCollectWordsTheme {
        CollectWordsResultScreen(words = listOf(), removeWord = {})
    }
}