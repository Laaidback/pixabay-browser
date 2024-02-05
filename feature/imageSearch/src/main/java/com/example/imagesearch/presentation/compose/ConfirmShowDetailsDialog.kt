package com.example.imagesearch.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.presentation.model.DialogModel

@Composable
fun ConfirmShowDetailsDialog(
    dialogModel: DialogModel
) {
    AlertDialog(
        onDismissRequest = { dialogModel.onDismiss() },
        title = { Text(text = "Show image details?") },
        confirmButton = {
            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { dialogModel.onConfirm() },
                text = "yes"
            )
        },
        dismissButton = {
            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { dialogModel.onDismiss() },
                text = "no"
            )
        },
    )
}
