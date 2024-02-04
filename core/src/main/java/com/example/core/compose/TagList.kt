package com.example.core.compose

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<String>) {
    FlowRow {
        tags.forEach { tag ->
            SuggestionChip(
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 8.dp)
                    .padding(top = 8.dp),
                onClick = { },
                label = { Text(text = tag) }
            )
        }
    }
}
