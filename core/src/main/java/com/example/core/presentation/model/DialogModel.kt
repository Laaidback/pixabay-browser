package com.example.core.presentation.model

data class DialogModel(
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
)
