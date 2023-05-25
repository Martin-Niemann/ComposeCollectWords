package com.niemannsolutions.composecollectwords.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WordItem(word: String, removeWord: ((String) -> Unit)?) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
            .padding(start = 8.dp)
            .fillMaxWidth()
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