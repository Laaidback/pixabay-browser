package com.example.core.presentation.compose

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.presentation.model.ChipModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<ChipModel>) {
    FlowRow {
        tags.forEach { chipModel ->
            SuggestionChip(
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 8.dp)
                    .padding(top = 8.dp),
                onClick = chipModel.onClick,
                label = { Text(text = chipModel.title) }
            )
        }
    }
}
